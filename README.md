mvn clean install.

docker build -t customer -f ./docker-images/Dockerfile .
docker build -t product -f ./docker-images/Dockerfile .
docker build -t eureka -f ./docker-images/Dockerfile .
docker build -t gateway -f ./docker-images/Dockerfile .

kubectl get pods -o wide
kubectl get deployment -o wide
kubectl get service -o wide

kubectl apply -f deployment.yaml
kubectl apply -f service.yaml

kubectl scale --replicas=3 deployment/eureka-deployment
kubectl scale --replicas=3 deployment/gateway-deployment
kubectl scale --replicas=3 deployment/customer-deployment
kubectl scale --replicas=3 deployment/product-deployment

kubectl scale --replicas=0 deployment/eureka-deployment
kubectl scale --replicas=0 deployment/gateway-deployment
kubectl scale --replicas=0 deployment/customer-deployment
kubectl scale --replicas=0 deployment/product-deployment

kubectl delete deployment/eureka-deployment
kubectl delete deployment/gateway-deployment
kubectl delete deployment/customer-deployment
kubectl delete deployment/product-deployment

kubectl delete service eureka-deployment
kubectl delete service gateway-deployment
kubectl delete service customer-deployment
kubectl delete service product-deployment


Customer Service:

URL:
Post: http://localhost:1000/customer/add-customer
Post: http://localhost:1000/customer/feign-add-customer
Get: http://localhost:1000/customer/get-customer/99
Get: http://localhost:1000/customer/test-resilience4j

Header: customer:true

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
