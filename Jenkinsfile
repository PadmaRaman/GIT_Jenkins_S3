pipeline {
    agent any

    environment {
        AWS_DEFAULT_REGION = "ap-south-1"
        S3_BUCKET = "qa-test-artifacts"
        LOG_DIR = "logs"
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/PadmaRaman/GIT_Jenkins_S3.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Upload Logs to S3') {
            steps {
                withCredentials([[$class: 'AmazonWebServicesCredentialsBinding',
                                  credentialsId: 'aws-s3-creds']]) {
                    sh '''
                    aws s3 cp $LOG_DIR s3://$S3_BUCKET/logs/build-$BUILD_NUMBER/ --recursive
                    '''
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'logs/**/*.log', fingerprint: true
        }
        failure {
            echo 'Build failed – logs uploaded for analysis'
        }
    }
}
