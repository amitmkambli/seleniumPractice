pipeline{

    agent any

    stages{

        stage('Build jar'){
            steps{
                bat "mvn clean package -DskipTests"
            }
        }

        stage('Build image'){
            steps{
                bat "docker build -t amitdocker369/selfw:latest ."
            }            
        }  

        stage('Push image'){
            environment{
            	// assuming credentials are stored with name 'dockerhub-creds' in Manage Jenkins -> Credentials
                DOCKER_HUB = credentials('dockerhub-creds')
            }
            steps{
            	// note : we are using single quote as we need to pass the literal string
                bat 'docker login -u %DOCKER_HUB_USR% -p %DOCKER_HUB_PSW%'
                // bat 'echo %DOCKER_HUB_PSW% | docker login -u %DOCKER_HUB_USR% --password-stdin'
                bat "docker push amitdocker369/selfw:latest"
                // environment variable : Current Job -> Pipeline Syntax -> Global Variable Reference
                bat "docker tag amitdocker369/selfw:latest amitdocker369/selfw:${env.BUILD_NUMBER}"
                bat "docker push amitdocker369/selfw:${env.BUILD_NUMBER}"
            }            
        }    

    }
    post{
            always{
                bat "docker logout"
            }
        }

}