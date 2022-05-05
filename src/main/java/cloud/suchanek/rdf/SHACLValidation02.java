package cloud.suchanek.rdf;

import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.rio.RDFFormat;

public class SHACLValidation02 {

    private static final String SHAPE = """
@prefix ex:   <http://example.com/ns#> .
@prefix sh:   <http://www.w3.org/ns/shacl#> .
@prefix xsd:  <http://www.w3.org/2001/XMLSchema#> .
@prefix foaf: <http://xmlns.com/foaf/0.1/> .
                        
ex:PersonShape
  a sh:NodeShape  ;
  sh:targetClass foaf:Person ;
  sh:property ex:PersonShapeProperty .
                        
ex:PersonShapeProperty
  sh:path foaf:age ;
  sh:datatype xsd:int ;
  sh:maxCount 1 ;
  sh:minCount 1 .
""";

    private static final String DATA = """
@prefix ex: <http://example.com/ns#> .
@prefix foaf: <http://xmlns.com/foaf/0.1/>.
@prefix xsd: <http://www.w3.org/2001/XMLSchema#> .

ex:peter a foaf:Person ;
  foaf:age 20, "30"^^xsd:int .
""";

    public static void main(String[] args) {
        String baseUri = "http://example.com/ns#";
        Model shacl = RdfUtils.read(SHAPE, baseUri, RDFFormat.TURTLE);
        Model data = RdfUtils.read(DATA, baseUri, RDFFormat.TURTLE);

        // This example works...
        // from: https://rdf4j.org/documentation/programming/shacl/
        RdfUtils.validate(shacl, data, baseUri);
    }
}
