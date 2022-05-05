package cloud.suchanek.rdf;

import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.rio.RDFFormat;

public class SHACLValidation05 {

    private static final String SHAPE = """
@prefix ex:   <http://example.com/ns#> .
@prefix sh:   <http://www.w3.org/ns/shacl#> .
@prefix xsd:  <http://www.w3.org/2001/XMLSchema#> .
@prefix foaf: <http://xmlns.com/foaf/0.1/> .
@prefix dcat: <http://www.w3.org/ns/dcat#> .
@prefix dct:  <http://purl.org/dc/terms/> .
                        
ex:ResourceShape a sh:NodeShape ;
  sh:targetClass dcat:Resource ;
  sh:property [
    sh:path dct:publisher ;
    sh:node ex:AgentShape ;
    sh:minCount 1 ;
    sh:maxCount 1 ;
  ] .
                        
ex:AgentShape a sh:NodeShape ;
  sh:targetClass foaf:Agent ;
  sh:property [
    sh:path foaf:name ;
    sh:nodeKind sh:Literal ;
  ] .
""";

    private static final String DATA = """
@prefix ex:   <http://example.com/ns#> .
@prefix sh:   <http://www.w3.org/ns/shacl#> .
@prefix xsd:  <http://www.w3.org/2001/XMLSchema#> .
@prefix foaf: <http://xmlns.com/foaf/0.1/> .
@prefix dcat: <http://www.w3.org/ns/dcat#> .
@prefix dct:  <http://purl.org/dc/terms/> .

ex:resource a dcat:Resource .
""";

    public static void main(String[] args) {
        String baseUri = "http://example.com/ns#";
        Model shacl = RdfUtils.read(SHAPE, baseUri, RDFFormat.TURTLE);
        Model data = RdfUtils.read(DATA, baseUri, RDFFormat.TURTLE);

        // This fails intentionally (missing publisher for a resource)
        RdfUtils.validate(shacl, data, baseUri);
    }
}
