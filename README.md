# admin-test-api
 
## Changes
- Added JwtFilter.java && WebSecurityConfig.java into configuration package to handle filtering and security configuration
- Changes in AdminController.java in Controller package to alter the responses to the one contains token
- Added LoginResponse.java to dto package to hold the token and user info as login API response body 
- Changes in AdminService.java to handle the new token requirements 
- Added new Package util to add new JWT util class to support JWT operations 
- Changes in UserService.java to add logger to user creation logic

## JWT Token 
- Use the login API to obtain JWT token in the response body
- To use the other APIs, go to the APi tab in Postman then go to the authorization section in postman and use the token type as Bearer Token 
- Add the token form the login response time to acquire access to the API 
- *NOTE* : The token live time is 60 minutes, the value can be found in JWT util class in accessTokenValidity field  