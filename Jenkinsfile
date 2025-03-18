pipeline{
    agent any

    environment{
        DOCKER_TAG = "V.${BUILD_NUMBER}"
        DOCKER_IMAGE_NAME = "myapp"
        DOCKER_HUB_USERNAME = "sweetcoder7"
        IMAGE_URL = "${DOCKER_HUB_USERNAME}/${DOCKER_IMAGE_NAME}:${DOCKER_TAG}"
    }

    stages{
        stage('imageBuild'){
            steps{
                sh "docker build -t ${IMAGE_URL} ."
            }
        }

        stage('imagePush'){
            steps{
                withCredentials([usernamePassword(credentialsId: 'DockerHubID', passwordVariable: 'PASS', usernameVariable: 'USER')]){
                sh "echo $PASS | docker login -u $USER --password-stdin"
                sh "docker push ${IMAGE_URL}"
                }
            }           
        }

        stage('imageRemove'){
            steps{
                sh "docker rmi ${IMAGE_URL}"
            }
        }
    }

post {
    success {
        echo 'Docker image has been build, pushed to DockerHub and now triggering deploy'
        build job: 'docker-deploy',
            parameters: {
                sstring (name: 'IMAGE_URL', value: "$IMAGE_URL")
            }
    }
    failure {
        echo 'Something wrong'
    }
}
}