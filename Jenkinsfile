pipeline{
    agent any

    environment{
        DOCKER_TAG = "V.${BUILD_NUMBER}"
        DOCKER_IMAGE_NAME = "myapp"
        DOCKER_HUB_USERNAME = "sweetcoder7"
        IMAGE_URL = ${DOCKER_HUB_USERNAME}/${DOCKER_IMAGE_NAME}:${DOCKER_TAG}
    }

    stages{
        stage('imageBuild'){
            steps{
                sh "docker build -t ${IMAGE_URL} ."
            }
        }

        stage('imagePush'){
            steps{
                sh "docker push ${IMAGE_URL}"
            }           
        }

        // stage('imageRemove'){

        // }
    }
}