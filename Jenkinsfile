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
    }
}


