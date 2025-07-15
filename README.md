## Cart Service

* This service handles the functionality like adding items to the cart, updating the quantity of cart item, clearing the cart and deleting the added item to the cart.

**API Endpoints**

### 1. `[GET]` api/cart
To get the list of products added to the cart by the user.

Request Header(s)
Authorization: Bearer [JWT token]

Response body:
````json
{
 "userId":159,
 "cartId":123,
 "cartPrice":5000
 "items": [{
  "itemId":1,
  "productId":25,
  "productName":"Dell keyboard 115"
  "quantity":2,
  "unitPrice":2500 
  }]
}
````

### 2.`[POST]` api/cart/add
To add a product to the cart

Request Header(s)
Authorization: Bearer [JWT token]

Sample request body:
````json
{
 "productId":25,
 "productName":"Dell keyboard 115",
 "quantity":2,
 "unitPrice":2500
}
````

Sample response body:
````json
{
 "userId":159,
 "cartId":123,
 "cartPrice":5000
 "items": [{
  "itemId":1,
  "productId":25,
  "productName":"Dell keyboard 115"
  "quantity":2,
  "unitPrice":2500 
  }]
}
````

### 3.`[PUT]`  api/cart/item/{itemId}/quantity/{quantity}
To update the quantity of an existing cart item.

**Request Header(s):**
Authorization: Bearer [JWT token]

**Path parameters:**
* itemId*
* quantity*

**sample request URL:**  **api/cart/item/1/quantity/4**

Sample response body:
````json
{
 "userId":159,
 "cartId":123,
 "cartPrice":5000
 "items": [{
  "itemId":1,
  "productId":25,
  "productName":"Dell keyboard 115"
  "quantity":4,
  "unitPrice":2500 
  }]
}
````

### 4.`[DELETE]` api/cart/item{itemId}
To delete an existing cart item form the cart

**Request Header(s):**
Authorization: Bearer [JWT token]

**Path parameters:**
* itemId*

**sample request URL:**  **api/cart/item/1**

Sample response body:
````json
{
 "userId":159,
 "cartId":123,
 "cartPrice":5000
 "items": []
}
````

### 5.`[DELETE]` api/cart
To clear the all added cart items from the cart

**Request Header(s):**
Authorization: Bearer [JWT token]

**Path parameters:**
* itemId*

**sample request URL:**  **api/cart**

Sample response body:
````json
{
 "userId":159,
 "cartId":123,
 "cartPrice":5000
 "items": []
}
````

**Database Tables:**
* **cart**
  * id
  * userid
  * created_at
  * updated_at
       
* **cart_item**
  * id
  * cart_id
  * product_id
  * qunatity
  * unit_price
  * created_at
  * updated_at
   

**Technologies:**
* Java17
* Spring Boot
* My SQL DB

**Maven dependencies:**
* lombok
* mysql-connector-j
* spring-boot-starter-data-jpa
* jjwt-api
* jjwt-impl
* jjwt-jackson





