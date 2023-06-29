pipeline {
    agent any
    tools{
        maven 'MAVEN_HOME'
        jdk 'SMARTAPPS_JAVA_HOME'
    }
    stages{
        stage('Stage 1 - Checkout code'){
            steps{
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/Shylendra/SmartUser']])
            }
        }
        stage('Stage 2 - Build Maven'){
            steps{
                bat 'mvn clean install'
            }
        }
        stage('Stage 3 - Build docker image'){
            steps{
                bat 'docker build -t shylendra2015/smartuser-api:latest .'
            }
        }
        stage('Stage 4 - Login and Push image to Dockerhub'){
            steps{
              	withCredentials([usernamePassword(credentialsId: 'dockerHub', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
                	bat "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}"
                  bat 'docker push shylendra2015/smartuser-api:latest'
                }            
            }
        }
   }
    post {
        always {
            bat 'docker logout'
        }
    }
}