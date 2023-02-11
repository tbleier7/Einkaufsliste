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
                sh 'mvn clean -Dmaven.test.failure.ignore=true install'
            }
//             post {
//                 success {
//                     junit 'target/surefire-reports/**/*.xml'
//                 }
//             }
        }

        stage ('Package') {
            steps {
                sshPublisher failOnError: true, publishers: [sshPublisherDesc(configName: 'ubuntu-house-server', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: '', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectorySDF: false, removePrefix: '', sourceFiles: 'target/*.jar')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: true)]
            }
        }
    }
}