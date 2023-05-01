pipeline {
    agent any
    tools {
        maven 'M3'
        jdk 'openJDK17'
    }
    stages {
        stage('Initialize') {
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

        stage('Build') {
            steps {
                sh 'mvn clean -Dmaven.test.failure.ignore=false package -Pproduction'
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml'
                }
                failure {
                    emailext body: 'The build failed, go look!', recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']], subject: 'BUILD FAILED!'
                }
            }
        }

        stage('Build Dockerimage') {
            steps {
                sh 'docker build . -t essensplanung:latest'
            }
        }
//        stage('Login to Github Container Registry') {
//            steps {
//                sshPublisher failOnError: true, publishers: [sshPublisherDesc(configName: 'kitchenlist_tu@UbuntuHouseServer', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: 'sudo systemctl restart kitchenlist.service', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '/webapps/kitchenlist', remoteDirectorySDF: false, removePrefix: 'target', sourceFiles: 'target/*.jar')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: true)]
//            }
//        }
//        stage('tag Essensplanung-Image') {
//            steps {
//                sshPublisher failOnError: true, publishers: [sshPublisherDesc(configName: 'kitchenlist_tu@UbuntuHouseServer', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: 'sudo systemctl restart kitchenlist.service', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '/webapps/kitchenlist', remoteDirectorySDF: false, removePrefix: 'target', sourceFiles: 'target/*.jar')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: true)]
//            }
//        }
//        stage('push image') {
//            steps {
//                sshPublisher failOnError: true, publishers: [sshPublisherDesc(configName: 'kitchenlist_tu@UbuntuHouseServer', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: 'sudo systemctl restart kitchenlist.service', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '/webapps/kitchenlist', remoteDirectorySDF: false, removePrefix: 'target', sourceFiles: 'target/*.jar')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: true)]
//            }
//        }




        //         stage ('Deploy') {
//             steps {
//                 sshPublisher failOnError: true, publishers: [sshPublisherDesc(configName: 'kitchenlist_tu@UbuntuHouseServer', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: 'sudo systemctl restart kitchenlist.service', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '/webapps/kitchenlist', remoteDirectorySDF: false, removePrefix: 'target', sourceFiles: 'target/*.jar')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: true)]
//             }
//         }
    }
}