pipeline {
    agent any

    tools {
        maven 'Maven-3.9.6'
        jdk 'Java-21'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Start Selenium Grid') {
            steps {
                sh 'docker compose -f docker/docker-compose.yml up -d'
            }
        }

        stage('Wait for Grid') {
            steps {
                sh '''
                    timeout 120 bash -c \
                        'until curl -sf http://localhost:4444/status; do
                            echo "Waiting for Grid..."; sleep 5;
                        done'
                    echo "Grid is ready"
                    sleep 10
                '''
            }
        }

        stage('Run Tests') {
            steps {
                sh '''
                    ./mvnw test \
                        -Dtestng.suiteXml=src/test/resources/testng-grid.xml \
                        -DGRID_URL=http://localhost:4444
                '''
            }
        }

        stage('SonarCloud Scan') {
            environment {
                SONAR_TOKEN = credentials('SONAR_TOKEN')
            }
            steps {
                sh '''
                    ./mvnw sonar:sonar \
                        -Dsonar.projectKey=naveendxavi05_ci-cd-pipeline-suite \
                        -Dsonar.organization=naveendxavi05 \
                        -Dsonar.host.url=https://sonarcloud.io \
                        -Dsonar.token=${SONAR_TOKEN}
                '''
            }
        }

        stage('Generate Allure Report') {
            steps {
                sh 'allure generate allure-results --clean -o allure-report'
            }
        }
    }

    post {
        always {
            sh 'docker compose -f docker/docker-compose.yml down'
            archiveArtifacts artifacts: 'allure-report/**', allowEmptyArchive: true
        }
        success {
            echo 'Pipeline completed successfully'
        }
        failure {
            echo 'Pipeline failed'
        }
    }
}