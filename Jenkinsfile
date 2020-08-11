#!/usr/bin/env groovy
@Library("convair")
@Library("vv-convair-hub@develop")


import br.com.viavarejo.jornada.jenkins.stages.common.DefineType
import br.com.viavarejo.jornada.jenkins.stages.common.RootCleanup
import br.com.viavarejo.jornada.jenkins.stages.docker.DockerBuildAndPush
import br.com.viavarejo.jornada.jenkins.stages.docker.DockerBuildAndPushParameter
import br.com.viavarejo.jornada.jenkins.stages.git.GitVersion
import br.com.viavarejo.jornada.jenkins.stages.maven.MavenBuild
import br.com.viavarejo.jornada.jenkins.stages.maven.MavenBuildParameters
import br.com.viavarejo.jornada.jenkins.stages.maven.MavenDeploy
import br.com.viavarejo.jornada.jenkins.stages.maven.MavenDeployParameters
import br.com.viavarejo.jornada.jenkins.stages.maven.MavenSetVersion
import br.com.viavarejo.jornada.jenkins.stages.maven.MavenSetVersionParameter
import br.com.viavarejo.jornada.jenkins.stages.maven.MavenSonar
import br.com.viavarejo.jornada.jenkins.stages.maven.MavenSonarParameters
import br.com.viavarejo.jornada.jenkins.stages.maven.MavenUnitTest
import br.com.viavarejo.jornada.jenkins.stages.maven.MavenUnitTestParameters
import br.com.viavarejo.jornada.jenkins.stages.sonarqube.QualityGate
import br.com.viavarejo.jornada.jenkins.stages.xlrelease.XLReleasePromoteApplication

Convair {


    variables = [
            NAMESPACE_PREFIX   : "prove-manualdeloja",
            PROJECT_NAME       : 'prove-manualdeloja-aks',
            APPLICATION_NAMES  : 'prove-manualdeloja-aks',
            SQUAD_NAME         : 'PROVE-RH DevOps',
            SERVICE_NOW_CI     : '621156e91b465c90574265b33a4bcb00',
            SOURCE_REGISTRY    : 'harbor01.viavarejo.com.br',
            DEPLOYMENT_STRATEGY: 'aks'
    ]
    selectedAgent = "master"
    selectedStages = [

            DefineType      : DefineType.use(),

            MavenSetVersion : MavenSetVersion.use(new MavenSetVersionParameter(
                    maven: "M3", mavenSettingsConfig: "maven-settings"
            ),{ env.SHOULD_DEPLOY }, "maven:3-jdk-11"),

            MavenUnitTest   : MavenUnitTest.use(
                    new MavenUnitTestParameters(maven: 'M3', mavenSettingsConfig: 'simple-maven-settings'),
                    { true }, "maven:3-jdk-11"),

            MavenSonar      : MavenSonar.use(new MavenSonarParameters(maven: 'M3',
                    mavenSettingsConfig: 'simple-maven-settings',
                    springProfile: 'docker'), { env.SHOULD_DEPLOY}, "maven:3-jdk-11"),

            QualityGate     : QualityGate.use(),

            MavenBuid       : MavenBuild.use(
                        new MavenBuildParameters(maven: 'M3', mavenSettingsConfig: 'simple-maven-settings')
            , { true }, "maven:3-jdk-11"),

            GitVersion      : GitVersion.use(),

            MavenDeploy     : MavenDeploy.use(
                    new MavenDeployParameters(maven: 'M3', mavenSettingsConfig: 'simple-maven-settings'),
                    { env.SHOULD_DEPLOY },
                    "maven:3-jdk-11"
            ),

            BuildDockerImage: DockerBuildAndPush.use(new DockerBuildAndPushParameter(registry: 'harbor01.viavarejo.com.br',
                    registryCredentialsId: 'harbor-registry')),

            StartRelease    : XLReleasePromoteApplication.use(),

            RootCleanup: RootCleanup.use()

    ]

    onFailure = {
        if (env.COMMITER_EMAIL) {
            step([$class                  : 'Mailer',
                  notifyEveryUnstableBuild: true,
                  recipients              : env.COMMITER_EMAIL,
                  sendToIndividuals       : true]
            )
        }
    }
}
