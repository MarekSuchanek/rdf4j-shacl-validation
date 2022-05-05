package cloud.suchanek.rdf;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.model.vocabulary.RDF4J;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.repository.sail.SailRepositoryConnection;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFHandlerException;
import org.eclipse.rdf4j.rio.RDFParseException;
import org.eclipse.rdf4j.rio.Rio;
import org.eclipse.rdf4j.sail.memory.MemoryStore;
import org.eclipse.rdf4j.sail.shacl.ShaclSail;
import org.eclipse.rdf4j.sail.shacl.ShaclSailValidationException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class RdfUtils {

    private static final ValueFactory VF = SimpleValueFactory.getInstance();

    public static IRI i(String iri) {
        if (iri == null) {
            return null;
        }
        return VF.createIRI(iri);
    }

    public static Model read(String content, String baseUri, RDFFormat format) {
        try (InputStream inputStream = new ByteArrayInputStream(content.getBytes())) {
            return Rio.parse(inputStream, baseUri, format);
        } catch (IOException e) {
            throw new RuntimeException("Unable to read RDF (IO exception)");
        } catch (RDFParseException e) {
            throw new RuntimeException("Unable to read RDF (parse exception)");
        } catch (RDFHandlerException e) {
            throw new RuntimeException("Unable to read RDF (handler exception)");
        }
    }

    public static void validate(Model shacl, Model data, String baseUri) {
        ShaclSail shaclSail = new ShaclSail(new MemoryStore());
        shaclSail.setRdfsSubClassReasoning(true);
        //shaclSail.setUndefinedTargetValidatesAllSubjects(true);
        SailRepository sailRepository = new SailRepository(shaclSail);
        sailRepository.init();

        try (SailRepositoryConnection connection = sailRepository.getConnection()) {
            // 2. Save SHACL
            connection.begin();
            connection.add(shacl, RDF4J.SHACL_SHAPE_GRAPH);
            connection.commit();

            // 3. Validate data
            connection.begin();
            connection.add(new ArrayList<>(data), i(baseUri));
            connection.commit();

        } catch (RepositoryException exception) {
            Throwable cause = exception.getCause();
            if (cause instanceof ShaclSailValidationException) {
                Model validationReportModel = ((ShaclSailValidationException) cause).validationReportAsModel();
                Rio.write(validationReportModel, System.out, RDFFormat.TURTLE);
            }
            throw new RuntimeException("Validation failed (unsupported exception)");
        }
    }

}
