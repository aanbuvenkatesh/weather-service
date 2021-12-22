pipeline {
    agent any

    parameters {
        choice(
                name: 'ENVIRONMENT',
                choices: [
                        'dev',
                        'qa',
                        'stage',
                        'prod'
                ],
                description: 'Deployment Environment'
        )
        string(name: 'AWS_ACCOUNT_ID', defaultValue: '935786470911', description: 'Enter the AWS Account Id')
        choice(
                name: 'AWS_REGION',
                choices: [
                        ' ',
                        'ap-south-1',
                        'us-east-1',
                        'us-east-2'
                ],
                description: 'AWS Account Region'
        )
        booleanParam(name: 'CONFIRMATION', defaultValue: false, description: 'Are you sure you want to perform this action?')
    }

    environment {
        awsAccountId = "${AWS_ACCOUNT_ID}"
        awsRegion = "${AWS_REGION}"
        dockerRegistry = "${AWS_ACCOUNT_ID}" + ".dkr.ecr." + "${AWS_REGION}" + ".amazonaws.com/"
        deploymentdate = "${deploymentdate()}"
    }

    stages {
        stage('Pre-Check') {
            steps {
                script {
                    def consent = "${CONFIRMATION}"
                    if (consent == false) {
                        error("Confirm the Deployment before Proceeding")
                    }
                }
            }
        }
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/aanbuvenkatesh/weather-service'
            }
        }
        stage('Build') {
            options {
                timeout(time: 1, unit: 'HOURS')
            }
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew clean build'
            }
        }
        stage('Package') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew app_package'
            }
        }
        stage('Build Docker Image') {
            steps {
                sh 'rm -f ~/.dockercfg ~/.docker/config.json || true'
                unzip zipFile: 'weather-prediction-infra/build/weather-service-app-DEV.0.0-SNAPSHOT.zip', dir: 'temp/docker/'
                script {
                    docker.build(appImageId(), "temp/docker/")
                }
            }
        }
        stage('Publish Docker Images') {
            steps {
                script {
                    docker.withRegistry(appRegistryURI(), "ecr:ap-south-1:ecr-weather-repository-credential") {
                        docker.image(appImageId() + ':latest').push("${env.BUILD_NUMBER}")
                        docker.image(appImageId() + ':latest').push("latest")
                    }
                }
            }
        }
        stage('Deploy') {
            steps {
                sh 'chmod +x ci-cd-artifacts/scripts/deploy-stack.sh'
                withCredentials([[
                                         $class           : 'AmazonWebServicesCredentialsBinding',
                                         credentialsId    : "ecr-weather-repository-credential",
                                         accessKeyVariable: 'AWS_ACCESS_KEY_ID',
                                         secretKeyVariable: 'AWS_SECRET_ACCESS_KEY']]) {
                    sh 'ci-cd-artifacts/scripts/deploy-stack.sh weather-service-${ENVIRONMENT} ${ENVIRONMENT}.properties weather-service.yaml true ${AWS_REGION}'
                }
            }
        }
    }

    post {
        always {
            echo "Cleaning Up"
            sh(script: 'docker system prune --volumes -f')
            cleanWs()
        }
        failure {
            notifyfailure()
        }
    }
}

def appImageId() {
    def weatherAppImageName = "weather-service/app"
    return "${dockerRegistry}" + weatherAppImageName
}

def appRegistryURI() {
    return 'https://' + appImageId()
}

def notifyfailure() {
    echo """Build Failed"""
}

def commitdate() {
    sh(script: 'date -d @$(git log -n1 --format=\'%at\') +%Y:%m:%d-%H:%M:%S', returnStdout: true).trim()
}

def deploymentdate() {
    sh(script: 'date +"%Y:%m:%d-%H:%M:%S"', returnStdout: true).trim()
}

def commitId() {
    sh(script: 'git rev-list --max-count=1 --abbrev-commit --skip=# HEAD', returnStdout: true).trim()
}

def commitowner() {
    sh(script: 'git --no-pager show -s --format=\'%ae\'', returnStdout: true).trim()
}
