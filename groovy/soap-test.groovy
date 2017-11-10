@Grab( 'com.github.groovy-wslite:groovy-wslite:0.8.0' )
import wslite.soap.*
import wslite.http.auth.*
import groovy.xml.XmlUtil

def client = new SOAPClient( 'https://test.anis.ch/HTDB.WebService/AnimalImportService.asmx' )

client.authorization = new HTTPBasicAuthorization( "613731", "test" )

// Trust the ssl for this site
client.httpClient.sslTrustAllCerts = true

def response = client.send(SOAPAction:'urn:tvd:heimtierdatenbanksql:webservice:animalimportservcie:v1:getAnimalsIn') {
    body {
        getAnimals( 'xmlns':'urn:tvd:heimtierdatenbanksql:webservice:animalimportmessages:v1' ) {
            getMessage {
                Header( 'xmlns':'urn:tvd:heimtierdatenbanksql:webservice:animalimportdata:v1' ) {
                    P_Praxisnummer( '371066' )
                    P_Account( '613731' )
                    P_PIN( 'test' )
                }
                Body( 'xmlns':'urn:tvd:heimtierdatenbanksql:webservice:animalimportdata:v1' ) {
                    KZ_Kennzeichnung( '756000100230345' )
                }
            }
        }
    }
}

println XmlUtil.serialize( response.getAnimalResponse )