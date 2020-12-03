# Springboot-docker-postgres
todo
1. Project has use Postgres database, docker compose, spring boot 
2. Cài đặt Postgres database using docker-compose
docker run -it --rm --name my-postgres -v /data/postgres:/var/lib/postgresql/data -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres
https://experto.dev/en/how-to-create-a-microservice-with-spring-boot-and-docker/  : create new database and table using command

3. Run docker
docker-compose up --build -d
Stop docker compose: docker-compose -f /home/gtgt/khanhbn/springboot-postgres-docker/docker-compose.yml down
Xóa repo <none>:  docker images | grep none | awk '{ print $3; }' | xargs docker rmi
Clean package maven ignore test: mvn clean compile package -DskipTests=true

4. Bổ sung xuất excel sử dụng thư viện JXLS
5. Upload/Download file sử dụng minio server 10.22.7.121:9000 (vnpay/vnpay@123) 
