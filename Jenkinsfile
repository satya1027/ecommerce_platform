pipeline {
    agent any

    tools {
        maven 'maven'
        jdk 'JDK21'
    }

    environment {
        DOCKER_USERNAME = "satya1027"
        SCANNER_HOME = tool 'sonar-scanner'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Microservices') {
            steps {
                sh '''
                cd user-service && ./mvnw clean package -DskipTests && cd ..

                cd product-service && ./mvnw clean package -DskipTests && cd ..

                cd order-service && ./mvnw clean package -DskipTests && cd ..

                cd payment-service && ./mvnw clean package -DskipTests && cd ..

                cd notification-service && ./mvnw clean package -DskipTests && cd ..

                cd api-gateway && ./mvnw clean package -DskipTests && cd ..
                '''
            }
        }

        stage('SonarQube Scan') {
            steps {
                withSonarQubeEnv('SonarQube') {

                    sh '''
                    cd user-service
                    $SCANNER_HOME/bin/sonar-scanner \
                    -Dsonar.projectKey=user-service \
                    -Dsonar.sources=. \
                    -Dsonar.java.binaries=target/classes
                    cd ..

                    cd product-service
                    $SCANNER_HOME/bin/sonar-scanner \
                    -Dsonar.projectKey=product-service \
                    -Dsonar.sources=. \
                    -Dsonar.java.binaries=target/classes
                    cd ..

                    cd order-service
                    $SCANNER_HOME/bin/sonar-scanner \
                    -Dsonar.projectKey=order-service \
                    -Dsonar.sources=. \
                    -Dsonar.java.binaries=target/classes
                    cd ..

                    cd payment-service
                    $SCANNER_HOME/bin/sonar-scanner \
                    -Dsonar.projectKey=payment-service \
                    -Dsonar.sources=. \
                    -Dsonar.java.binaries=target/classes
                    cd ..

                    cd notification-service
                    $SCANNER_HOME/bin/sonar-scanner \
                    -Dsonar.projectKey=notification-service \
                    -Dsonar.sources=. \
                    -Dsonar.java.binaries=target/classes
                    cd ..

                    cd api-gateway
                    $SCANNER_HOME/bin/sonar-scanner \
                    -Dsonar.projectKey=api-gateway \
                    -Dsonar.sources=. \
                    -Dsonar.java.binaries=target/classes
                    cd ..
                    '''
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 10, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Trivy File System Scan') {
            steps {
                sh '''
                trivy fs . \
                --severity HIGH,CRITICAL \
                --exit-code 0
                '''
            }
        }

        stage('Build Docker Images') {
            steps {
                sh '''
                docker build -t satya1027/user-service:v1 user-service
                docker build -t satya1027/product-service:v1 product-service
                docker build -t satya1027/order-service:v1 order-service
                docker build -t satya1027/payment-service:v1 payment-service
                docker build -t satya1027/notification-service:v1 notification-service
                docker build -t satya1027/api-gateway:v1 api-gateway
                '''
            }
        }

        stage('Trivy Image Scan') {
            steps {
                sh '''
                trivy image satya1027/user-service:v1 --severity HIGH,CRITICAL --exit-code 0
                trivy image satya1027/product-service:v1 --severity HIGH,CRITICAL --exit-code 0
                trivy image satya1027/order-service:v1 --severity HIGH,CRITICAL --exit-code 0
                trivy image satya1027/payment-service:v1 --severity HIGH,CRITICAL --exit-code 0
                trivy image satya1027/notification-service:v1 --severity HIGH,CRITICAL --exit-code 0
                trivy image satya1027/api-gateway:v1 --severity HIGH,CRITICAL --exit-code 0
                '''
            }
        }

        stage('Push Docker Images') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {

                    sh '''
                    echo "$PASSWORD" | docker login -u "$USERNAME" --password-stdin

                    docker push satya1027/user-service:v1
                    docker push satya1027/product-service:v1
                    docker push satya1027/order-service:v1
                    docker push satya1027/payment-service:v1
                    docker push satya1027/notification-service:v1
                    docker push satya1027/api-gateway:v1

                    docker logout
                    '''
                }
            }
        }

        stage('Cleanup') {
            steps {
                sh '''
                docker image prune -f
                '''
            }
        }
    }

    post {

        success {
            echo 'Pipeline Executed Successfully'
        }

        failure {
            echo 'Pipeline Failed'
        }
    }
}
