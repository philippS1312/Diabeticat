
# STOP all Containers:
docker stop customer-wildfly
docker stop customer-db
docker stop film-db 
docker stop film-wildfly  
docker stop store-db 
docker stop store-wildfly
docker stop ui-wildfly

# remove all Containers:
docker rm -v customer-wildfly 
docker rm -v customer-db 
docker rm -v film-db 
docker rm -v film-wildfly  
docker rm -v store-db 
docker rm -v store-wildfly
docker rm -v ui-wildfly

# remove all Images:
docker rmi ftse/customer-wildfly 
docker rmi ftse/customer-db 
docker rmi ftse/film-db 
docker rmi ftse/film-wildfly  
docker rmi ftse/store-db 
docker rmi ftse/store-wildfly
docker rmi ftse/ui-wildfly
