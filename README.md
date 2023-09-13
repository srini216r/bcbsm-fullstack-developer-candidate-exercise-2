CREATE A MONGODB with db name userdb


INVALID REQUEST 

curl --location 'http://localhost:8080/api/register' \
--header 'Content-Type: application/json' \
--data-raw '{
"firstName": "firsdst",
"lastName": "last",
"dateOfBirth": "01-15-1990",
"streetAddress": "street address",
"city": "Example City",
"state": "CA",
"zipCode": "123415",
"username": "johndoe123@@#",
"password": "Password123@"
}
'




VALID REQUEST 

curl --location 'http://localhost:8080/api/register' \
--header 'Content-Type: application/json' \
--data-raw '{
"firstName": "firsdst",
"lastName": "last",
"dateOfBirth": "01-15-1990",
"streetAddress": "street address",
"city": "Example City",
"state": "CA",
"zipCode": "12341",
"username": "johndoe123",
"password": "Password123@"
}
'



