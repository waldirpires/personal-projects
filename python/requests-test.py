import requests

address = 'vmx-rm-dfe-026'
port = '8080'

url = 'http://' + address + ':' + port + '/cpi/orchestration/v1/bi'
data = '''
{
    "interactionType": "Request",
    "biSpecificationRef": "/cpi/schemas/AcquireIndividualPartyBI-1.0.0.spec",
    "biItems": [
        {
            "description": "Optional description",
            "biItemSpecificationRef": "/cpi/schemas/PartyBIItem-1.0.0.spec",
            "action": "Add",
            "involvedEntity": {
                "entitySpecificationRef": "/cpi/schemas/Party.json",
                "entity": {
                    "partySpecificationRef": "Party.json",
                    "type": "individual",
                    "aliveDuring": {
                        "birthDate": "1987-01-21T02:19:00.000+03:00"
                    },
                    "countryOfBirth": "SE",
                    "maritalStatus": [
                        {
                            "maritalStatus": "Married",
                            "validFor": {
                                "start": "1982-02-28T02:19:00.000+03:00"
                            }
                        }
                    ],
                    "gender": "Male",
                    "languages": [
                        "en"
                    ],
                    "partyNames": [
                        {
                            "givenName": "Klaus Richard",
                            "familyNamePrefix": "von",
                            "familyName": "Ruebezahl",
                            "preferredGivenName": "Klaus",
                            "middleName": "",
                            "aristocraticTitle": "Baron",
                            "formattedName": "Mr Dr. Ruebezahl",
                            "qualification": "Dr. engineer",
                            "formOfAddress": "Mr",
                            "language": "en"
                        }
                    ],
                    "partyIdentifications": [
                        {
                            "idNumber": "76452348",
                            "issuingDate": "2015-06-24T12:36:02.556+02:00",
                            "commonName": "Passport",
                            "description": "The identification document ...",
                            "idType": "Passport",
                            "issuingCountry": "US"
                        }
                    ],
                    "contactMedia": [
                    ]
                }
            }
        }
    ]
}
'''
response = requests.post(url, data=data)
print response
print response.json