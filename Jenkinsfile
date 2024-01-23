pipeline {
agent any 
    stages {
        stage ('Build Backend') {
            steps {
                bat 'mvn clean package -DskipTests=true'
            }
        }
        stage ('Unit Tests') {
            steps {
                bat 'mvn test'
            }
        }
        stage ('Deploy Backend') {
            steps {
                deploy adapters: [tomcat9(credentialsId: 'Login_TomCat', path: '', url: 'http://localhost:8001/')], contextPath: null, war: 'target/tasks-backend.war'
            }
        }
        stage ('API Test') {
            steps {
                dir('api-test') {
                    git 'https://github.com/rodriguesxd7/tasks-api-tests'
                    bat 'mvn test'
                }
            }
        }
        stage ('Deploy Frontend') {
            steps {
                dir('frontend') {
                    git 'https://github.com/rodriguesxd7/tasks-frontend'
                    bat 'mvn clean package'
                    deploy adapters: [tomcat9(credentialsId: 'Login_TomCat', path: '', url: 'http://localhost:8001/')], contextPath: null, war: 'target/tasks.war'
                }
            }
        }
    }
}


