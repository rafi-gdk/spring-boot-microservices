CUSTOMER-SERVICR:-

URL:
http://localhost:1001/customer/add-customer

PAYLOAD:
{
"customerId": 96,
"customerName": "fake_data",
"products": [
{
"productId": 15,
"customerId": 77,
"productName": "fake_data",
"productFare": 67.53
},
{
"productId": 16,
"customerId": 77,
"productName": "fake_data",
"productFare": 67.53
}
, {
"productId": 17,
"customerId": 77,
"productName": "fake_data",
"productFare": 67.53
}
]
}

URL:
http://localhost:1001/product/get-products/96

PRODCUT-SERVICE:-

URL:
http://localhost:1001/product/add-products

PAYLOAD:
[
{
"productId": 15,
"customerId": 77,
"productName": "fake_data",
"productFare": 67.53
},
{
"productId": 16,
"customerId": 77,
"productName": "fake_data",
"productFare": 67.53
}
, {
"productId": 17,
"customerId": 77,
"productName": "fake_data",
"productFare": 67.53
}
]

URL:
http://localhost:1001/product/get-products/96

Step 1: mvn clean install
Step 2:
docker build -t customer -f .\docker-images\Dockerfile .
docker build -t product -f .\docker-images\Dockerfile .
docker build -t eureka -f .\docker-images\Dockerfile .
docker build -t gateway -f .\docker-images\Dockerfile .

