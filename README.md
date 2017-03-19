I advise to use Postman to test the application. 
With Postman you can add the required headers, body and authentication.

URLS:

http://localhost:8080/banking/swagger-ui.html - here it is possible to read and discover each endpoint and what body/parameter and verb is required

Authenticate:
POST http://localhost:8080/banking/oauth/token

Type Basic Authentication
username: clientIdPassword
password: secret

body (type x-www-form-urlencoded)
grant_type: password
username: banking@banking.com
password: 123456

As response you will receive a payload with access_token, just copy it and follow the next step.

If you try to access some url, such as

GET http://localhost:8080/banking/v1/account/1

without using the Header:

Authorization Bearer (paste access_token here)

You will be forbidden of doing that

Now, try to create the header

Authorization Bearer (paste access_token here)

and access GET http://localhost:8080/banking/v1/account/1

You will get the balance of the user with ID 1. That it is you!

POST http://localhost:8080/banking/v1/account/deposit
Header
Authorization Bearer (paste access_token here)

Body Type JSON/application
{
  "amount": 300,
  "idUser": 1
}


POST http://localhost:8080/banking/v1/account/withdraw
Header
Authorization Bearer (paste access_token here)

Body Type JSON/application
{
  "amount": 300,
  "idUser": 1
}

GET http://localhost:8080/banking/v1/account/{idUser} - always 1, because user is created when you run the microservices.
Header
Authorization Bearer (paste access_token here)


Try to access the /banking/v1/** urls without authenticating yourself! It would not be possible.

There is a oauth.sql that is performed when the application start, for creating the required oauth tables! 





