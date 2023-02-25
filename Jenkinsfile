pipeline {
    agent any
    tools {
        maven 'M3'
        jdk 'openJDK17'
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                    env | grep -e PATH -e JAVA_HOME
                    which java
                    java -version
                '''
            }
        }

        stage ('Build') {
            steps {
                sh 'mvn clean -Dmaven.test.failure.ignore=false install -Pproduction'
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml'
                }
                failure {
                    emailext body: 'A Test EMail', recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']], subject: 'Test'
                }
            }
        }

        stage ('Deploy') {
            steps {
                sshPublisher failOnError: true, publishers: [sshPublisherDesc(configName: 'ubuntu-house-server', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: 'sudo systemctl restart kitchenlist.service', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '/webapps/kitchenlist', remoteDirectorySDF: false, removePrefix: 'target', sourceFiles: 'target/*.jar')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: true)]
            }
        }
    }
}