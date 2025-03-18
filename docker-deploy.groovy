pipeline {
    agent any
    environment {
        EC2_IP = "18.233.154.14"
        CONTAINER_NAME = "myapp"
    }

    parameters{
        string (name: 'IMAGE_URL', defaultValue: '', description: 'Passedupstream job')
    }

    stages {
        stage('runContainer') {
            steps {
                sshagent(['jenkins-private-key']) {
                    def runContainer = "docker run -d -p 8080:8080 --name $CONTAINER_NAME $params.IMAGE_URL"
                    sh "ssh ubuntu@$EC2_IP $runContainer"
                }
            }
        }
    }
}
