pipeline {
    agent any

    tools {
        maven 'maven'
        jdk 'JDK21'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build User Service') {
            steps {
                dir('user-service') {
                    sh './mvnw clean install -DskipTests'
                }
            }
        }

        stage('Build Product Service') {
            steps {
                dir('product-service') {
                    sh './mvnw clean install -DskipTests'
                }
            }
        }

        stage('Build Order Service') {
            steps {
                dir('order-service') {
                    sh './mvnw clean install -DskipTests'
                }
            }
        }

        stage('Build Payment Service') {
            steps {
                dir('payment-service') {
                    sh './mvnw clean install -DskipTests'
                }
            }
        }

        stage('Build Notification Service') {
            steps {
                dir('notification-service') {
                    sh './mvnw clean install -DskipTests'
                }
            }
        }

        stage('Build API Gateway') {
            steps {
                dir('api-gateway') {
                    sh './mvnw clean install -DskipTests'
                }
            }
        }
    }

    post {
        success {
            echo 'Build Successful!'
        }

        failure {
            echo 'Build Failed!'
        }
    }
}
