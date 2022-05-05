package cloud.suchanek.rdf;

import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.rio.RDFFormat;

public class SHACLValidation01 {

    private static final String SHAPE = """
@prefix :         <http://fairdatapoint.org/> .
@prefix dash:     <http://datashapes.org/dash#> .
@prefix dcat:     <http://www.w3.org/ns/dcat#> .
@prefix dct:      <http://purl.org/dc/terms/> .
@prefix foaf:     <http://xmlns.com/foaf/0.1/> .
@prefix sh:       <http://www.w3.org/ns/shacl#> .
@prefix xsd:      <http://www.w3.org/2001/XMLSchema#> .
            
:ResourceShape a sh:NodeShape ;
  sh:targetClass dcat:Resource ;
  sh:property [
    sh:path dct:title ;
    sh:nodeKind sh:Literal ;
    sh:minCount 1 ;
    sh:maxCount  1 ;
    dash:editor dash:TextFieldEditor ;
  ], [
    sh:path dct:description ;
    sh:nodeKind sh:Literal ;
    sh:maxCount 1 ;
    dash:editor dash:TextAreaEditor ;
  ], [
    sh:path dct:publisher ;
    sh:node :AgentShape ;
    sh:minCount 1 ;
    sh:maxCount 1 ;
    dash:editor dash:BlankNodeEditor ;
  ], [
    sh:path dct:hasVersion ;
    sh:nodeKind sh:Literal ;
    sh:minCount 1 ;
    sh:maxCount 1 ;
    dash:editor dash:TextFieldEditor ;
    dash:viewer dash:LiteralViewer ;
  ], [
    sh:path dct:language ;
    sh:nodeKind sh:IRI ;
    sh:maxCount 1 ;
    dash:editor dash:URIEditor ;
    dash:viewer dash:LabelViewer ;
  ], [
    sh:path dct:license ;
    sh:nodeKind sh:IRI ;
    sh:maxCount 1 ;
    dash:editor dash:URIEditor ;
    dash:viewer dash:LabelViewer ;
  ], [
    sh:path dct:rights ;
    sh:nodeKind sh:IRI ;
    sh:maxCount 1 ;
    dash:editor dash:URIEditor ;
    dash:viewer dash:LabelViewer ;
  ] .
            
:AgentShape a sh:NodeShape ;
  sh:targetClass foaf:Agent ;
  sh:property [
    sh:path foaf:name ;
    sh:nodeKind sh:Literal ;
    sh:minCount 1 ;
    sh:maxCount 1 ;
    dash:editor dash:TextFieldEditor ;
  ] .
""";

    private static final String DATA = """
@prefix dcterms: <http://purl.org/dc/terms/> .
@prefix dcat: <http://www.w3.org/ns/dcat#> .
@prefix foaf: <http://xmlns.com/foaf/0.1/> .
@prefix xsd: <http://www.w3.org/2001/XMLSchema#> .
@prefix ldp: <http://www.w3.org/ns/ldp#> .

<https://purl.org/fairdatapoint/app> a dcat:Resource, dcat:DataService, <https://w3id.org/fdp/fdp-o#MetadataService>,
    <https://w3id.org/fdp/fdp-o#FAIRDataPoint>;
  dcterms:accessRights <https://purl.org/fairdatapoint/app#accessRights>;
  dcterms:language <http://id.loc.gov/vocabulary/iso639-1/en>;
  dcterms:license <http://rdflicense.appspot.com/rdflicense/cc-by-nc-nd4.0>;
  <http://semanticscience.org/resource/SIO_000628> <https://purl.org/fairdatapoint/app/metrics/445c0a70d1e214e545b261559e2842f4>,
    <https://purl.org/fairdatapoint/app/metrics/5d27e854a9e78eb3f663331cd47cdc13>;
  <http://www.re3data.org/schema/3-0#repositoryIdentifier> <https://purl.org/fairdatapoint/app#identifier>;
  <http://www.re3data.org/schema/3-0#institutionCountry> <https://www.wikidata.org/entity/Q55>;
  <http://www.re3data.org/schema/3-0#startDate> "2020-06-01T00:00:00.000Z"^^xsd:dateTime;
  <https://w3id.org/fdp/fdp-o#metadataCatalog> <https://purl.org/fairdatapoint/app/catalog/bce8a022-01d4-412b-b526-2679929f1c53>,
    <https://purl.org/fairdatapoint/app/catalog/508d3c96-8106-49d2-910c-13706aca75e3>,
    <https://purl.org/fairdatapoint/app/catalog/3dde263d-0a7c-4f2a-9813-ccb3a51f70a8>,
    <https://purl.org/fairdatapoint/app/catalog/a91d3db7-fe83-4de1-b678-fe1b0c82f2f1>,
    <https://purl.org/fairdatapoint/app/catalog/a46aa445-3be0-4db8-8968-3338d1061a47>,
    <https://purl.org/fairdatapoint/app/catalog/6e43c568-c6fa-41c8-a88a-7ee4c85035af>;
  <https://w3id.org/fdp/fdp-o#metadataIdentifier> <https://purl.org/fairdatapoint/app#identifier>;
  <https://w3id.org/fdp/fdp-o#metadataIssued> "2020-05-29T09:55:08.580Z"^^xsd:dateTime;
  <https://w3id.org/fdp/fdp-o#metadataModified> "2021-12-08T09:15:05.696Z"^^xsd:dateTime;
  <http://www.w3.org/2000/01/rdf-schema#label> "Demonstration FAIR Data Point";
  dcterms:description "This FAIR Data Point deployment is used for demonstration of the application and to allow the navigation through its metadata content. The metadata presented here is also for demonstration purposes only and not necessarily describe properly their related resources.";
  dcterms:hasVersion "1.0";
  dcterms:publisher <https://purl.org/fairdatapoint/app#publisher>;
  dcterms:title "Demonstration FAIR Data Point";
  dcterms:conformsTo <https://purl.org/fairdatapoint/app/profile/77aaad6a-0136-4c6e-88b9-07ffccd0ee4c>;
  <https://w3id.org/fdp/fdp-o#fdpSoftwareVersion> "FDP:v1.13.1~a55fcf5";
  dcat:endpointURL <https://purl.org/fairdatapoint/app> .

<https://purl.org/fairdatapoint/app/metrics/445c0a70d1e214e545b261559e2842f4> <http://semanticscience.org/resource/SIO_000332>
    <https://www.ietf.org/rfc/rfc3986.txt>;
  <http://semanticscience.org/resource/SIO_000628> <https://www.ietf.org/rfc/rfc3986.txt> .

<https://purl.org/fairdatapoint/app/metrics/5d27e854a9e78eb3f663331cd47cdc13> <http://semanticscience.org/resource/SIO_000332>
    <https://www.wikidata.org/wiki/Q8777>;
  <http://semanticscience.org/resource/SIO_000628> <https://www.wikidata.org/wiki/Q8777> .

<https://purl.org/fairdatapoint/app#accessRights> a dcterms:RightsStatement;
  dcterms:description "This resource has no access restriction" .

<https://purl.org/fairdatapoint/app#identifier> a <http://purl.org/spar/datacite/Identifier>;
  dcterms:identifier "https://purl.org/fairdatapoint/app" .

<https://purl.org/fairdatapoint/app#publisher> a foaf:Agent;
  foaf:name "FAIR Data Team" .

<https://purl.org/fairdatapoint/app/catalog/> a ldp:DirectContainer;
  dcterms:title "Catalogs";
  ldp:membershipResource <https://purl.org/fairdatapoint/app>;
  ldp:hasMemberRelation <https://w3id.org/fdp/fdp-o#metadataCatalog>;
  ldp:contains <https://purl.org/fairdatapoint/app/catalog/bce8a022-01d4-412b-b526-2679929f1c53>,
    <https://purl.org/fairdatapoint/app/catalog/508d3c96-8106-49d2-910c-13706aca75e3>,
    <https://purl.org/fairdatapoint/app/catalog/3dde263d-0a7c-4f2a-9813-ccb3a51f70a8>,
    <https://purl.org/fairdatapoint/app/catalog/a91d3db7-fe83-4de1-b678-fe1b0c82f2f1>,
    <https://purl.org/fairdatapoint/app/catalog/a46aa445-3be0-4db8-8968-3338d1061a47>,
    <https://purl.org/fairdatapoint/app/catalog/6e43c568-c6fa-41c8-a88a-7ee4c85035af> .

<https://purl.org/fairdatapoint/app/profile/77aaad6a-0136-4c6e-88b9-07ffccd0ee4c>
  <http://www.w3.org/2000/01/rdf-schema#label> "FAIR Data Point Profile" .
""";

    public static void main(String[] args) {
        String baseUri = "https://purl.org/fairdatapoint/app";
        Model shacl = RdfUtils.read(SHAPE, "http://fairdatapoint.org/", RDFFormat.TURTLE);
        Model data = RdfUtils.read(DATA, baseUri, RDFFormat.TURTLE);

        // This fails (real example)... simplified but same error in #03
        RdfUtils.validate(shacl, data, baseUri);
    }
}
