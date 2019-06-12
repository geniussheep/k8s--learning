//编译服务器设置start
def buildNodeSettings = [:]
buildNodeSettings.node = '阿里云Windows_2008_r2_x86'//编译服务器节点设置
buildNodeSettings.gitUrl = 'https://xxx/JenkinsPipelineProject.git'//git地址
buildNodeSettings.gitBarnches = '*/master' //分支
buildNodeSettings.slnFile = 'JenkinsPipelineProject.sln' //Nuget还原解决方案名

buildNodeSettings.buildFileForWeb ='JenkinsPipelineProjectWeb\\JenkinsPipelineProjectWeb.csproj' //msbulid编译文件名 web
buildNodeSettings.msbuildArgForWeb = '/t:Rebuild /p:Configuration=Release;PublishProfile=FolderProfile;DeployOnBuild=true' //msbulid参数 web
buildNodeSettings.publishOutputForWeb = '\\JenkinsPipelineProjectWeb\\bin\\Release\\PublishOutput' //编译后发布的路径 web
buildNodeSettings.publishFileNameForWeb = env.JOB_NAME + '/Build-Web-' +env.BUILD_NUMBER + '.7z'  //文件名 
buildNodeSettings.delFilesForWeb = ["Web.config","Web.Debug.config","Web.Release.config"] as String[]  //需要删除的文件

buildNodeSettings.buildFileForService ='JenkinsPipelineProject.sln' //msbulid编译文件名 Service
buildNodeSettings.msbuildArgForService = '/t:Rebuild /p:Configuration=Release' //msbulid参数 Service
buildNodeSettings.publishOutputForService = '\\JenkinsPipelineProjectWindowsService\\bin\\Release' //编译后发布的路径 Service
buildNodeSettings.publishFileNameForService = env.JOB_NAME + '/Build-Service-' +env.BUILD_NUMBER + '.7z'  //文件名 
buildNodeSettings.delFilesForService = ["*.config"] as String[]  //需要删除的文件

buildNodeSettings.updateServerPath = 'D:\\WebRoot\\update\\public_html\\'//更新服务器存放包地址
//编译服务器设置end

def webNodeSetting = [:]
webNodeSetting.node = '阿里云Windows_2008_r2_x86' //Web服务器节点
webNodeSetting.downloadPath = 'C:\\Jenkins\\download\\'//更新包下载地址
webNodeSetting.publishPath = 'D:\\WebRoot\\JenkinsPipelinePorject\\Web' //web服务器网站根目录
webNodeSetting.webApplicationName = 'JenkinsPipelinePorject'//web站点名称

def webNodeSetting2 = [:]
webNodeSetting2.node = 'master' //Web服务器节点
webNodeSetting2.downloadPath = 'C:\\JenkinsDownload\\'//更新包下载地址
webNodeSetting2.publishPath = 'D:\\WebRoot\\JenkinsPipelinePorject\\Web' //web服务器网站根目录
webNodeSetting2.webApplicationName = 'JenkinsPipelinePorject'//web站点名称

def webNodeSetting3 = [:]
webNodeSetting3.node = '京东云Windows_2008_r2_x64' //Web服务器节点
webNodeSetting3.downloadPath = 'C:\\Jenkins\\download\\'//更新包下载地址
webNodeSetting3.publishPath = 'C:\\WebRoot\\JenkinsPipelinePorject\\Web' //web服务器网站根目录
webNodeSetting3.webApplicationName = 'JenkinsPipelinePorject'//web站点名称

def serviceNodeSetting = [:]
serviceNodeSetting.node = '阿里云Windows_2008_r2_x86'
serviceNodeSetting.downloadPath = 'C:\\Jenkins\\download\\'//更新包下载地址
serviceNodeSetting.publishPath = 'D:\\WebRoot\\JenkinsPipelinePorject\\Service' //Service Windows Service存放路径
serviceNodeSetting.serviceName = 'JenkinsPipelineProject'//服务名称
serviceNodeSetting.serviceFileName = 'JenkinsPipelineProjectWindowsService.exe' //服务的文件名，相对publishPath的路径

def serviceNodeSetting2 = [:]
serviceNodeSetting2.node = 'master'
serviceNodeSetting2.downloadPath = 'C:\\Jenkins\\download\\'//更新包下载地址
serviceNodeSetting2.publishPath = 'D:\\WebRoot\\JenkinsPipelinePorject\\Service' //Service Windows Service存放路径
serviceNodeSetting2.serviceName = 'JenkinsPipelineProject'//服务名称
serviceNodeSetting2.serviceFileName = 'JenkinsPipelineProjectWindowsService.exe' //服务的文件名，相对publishPath的路径

def serviceNodeSetting3 = [:]
serviceNodeSetting3.node = '京东云Windows_2008_r2_x64'
serviceNodeSetting3.downloadPath = 'C:\\Jenkins\\download\\'//更新包下载地址
serviceNodeSetting3.publishPath = 'C:\\WebRoot\\JenkinsPipelinePorject\\Service' //Service Windows Service存放路径
serviceNodeSetting3.serviceName = 'JenkinsPipelineProject'//服务名称
serviceNodeSetting3.serviceFileName = 'JenkinsPipelineProjectWindowsService.exe' //服务的文件名，相对publishPath的路径

node(buildNodeSettings.node) {

    def msbuild=tool name: 'MSBuildTool V14.0', type: 'msbuild' //编译工具名称与地址
    buildNodeSettings.publishOutputForWeb  = env.WORKSPACE + buildNodeSettings.publishOutputForWeb 
    buildNodeSettings.publishOutputForService  = env.WORKSPACE + buildNodeSettings.publishOutputForService 
    
    stage('Check Out')
    {
        echo '检出项目'
        checkout([$class: 'GitSCM', branches: [[name: buildNodeSettings.gitBarnches]], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'xxxxxx', url: buildNodeSettings.gitUrl]]])
    }
    
    stage('Nuget Restore')
    {
        echo ' 还原nuget '
        echo '${env.nuget} restore "' + env.WORKSPACE + '/' + buildNodeSettings.slnFile + '" -ConfigFile "' + env.config + '" -NoCache'
        bat env.nuget + ' restore "' + env.WORKSPACE + '/' + buildNodeSettings.slnFile + '" -ConfigFile "' + env.config + '" -NoCache'
    }
        
    stage('Bulid')
    {
        echo ' 编译项目'
        echo 'Bulid Web'
        bat '"' + msbuild + '" ' + buildNodeSettings.msbuildArgForWeb + ' "' + env.WORKSPACE + '/' + buildNodeSettings.buildFileForWeb + '"'
        echo 'Bulid Service'
        bat '"' + msbuild + '" ' + buildNodeSettings.msbuildArgForService + ' "' + env.WORKSPACE + '/' + buildNodeSettings.buildFileForService + '"'
    }

    stage('Pack') {
        parallel PackWeb:{
            echo '删除相关配置文件'
            buildNodeSettings.delFilesForWeb.each{
                echo '删除文件：' + it
                def filepath ='"' + buildNodeSettings.publishOutputForWeb.replace("/","\\") + '\\' + it + '"'
                bat 'if exist '+ filepath +' del ' + filepath
            }
            echo ' 发布到更新系统'
            bat 'if not exist "' + buildNodeSettings.updateServerPath + env.JOB_NAME + '" md "' + buildNodeSettings.updateServerPath + env.JOB_NAME + '"'
            bat '"' + env.zip + '"'+ ' a -r "' + buildNodeSettings.updateServerPath + buildNodeSettings.publishFileNameForWeb + '" "' + buildNodeSettings.publishOutputForWeb + '\\*"'
            echo '压缩完成'
            echo '上传oss'
            bat env.oss + ' -c ' + env.ossconfig + ' cp "' + buildNodeSettings.updateServerPath + buildNodeSettings.publishFileNameForWeb + '" "oss://xxxx/' + buildNodeSettings.publishFileNameForWeb +'"'
        },
        PackService:{
            echo '删除相关配置文件'
            buildNodeSettings.delFilesForService.each{
                echo '删除文件：' + it
                def filepath ='"' + buildNodeSettings.publishOutputForService.replace("/","\\") + '\\' + it + '"'
                bat 'if exist '+ filepath +' del ' + filepath
            }
            echo ' 发布到更新系统'
             bat 'if not exist "' + buildNodeSettings.updateServerPath + env.JOB_NAME + '" md "' + buildNodeSettings.updateServerPath + env.JOB_NAME + '"'
            bat '"' + env.zip + '"'+ ' a -r "' + buildNodeSettings.updateServerPath + buildNodeSettings.publishFileNameForService + '" "' + buildNodeSettings.publishOutputForService + '\\*"'
            echo '压缩完成'
            echo '上传oss'
            bat env.oss + ' -c ' + env.ossconfig + ' cp "' + buildNodeSettings.updateServerPath + buildNodeSettings.publishFileNameForService + '" "oss://xxxx/' + buildNodeSettings.publishFileNameForService +'"'
        }
    }

    stage('Clear')
    {
        echo '清理工作目录'
        deleteDir()
    }
}

stage('Publish Web')
{
    parallel publishWeb1:{
        node(webNodeSetting.node)
        {
            echo '发布web'
            echo '更新文件'
            echo '更新文件下载地址为：http://xxxx/' + buildNodeSettings.publishFileNameForWeb
            echo '下载文件'
            bat env.oss + ' -c ' + env.ossconfig + ' cp  "oss://xxxx/' + buildNodeSettings.publishFileNameForWeb + '" ' + webNodeSetting.downloadPath
            echo '文件下载完成'
            echo '停止站点'
            bat 'C:\\Windows\\System32\\inetsrv\\appcmd.exe stop site "' + webNodeSetting.webApplicationName + '"'
            bat '"' + env.zip + '" x "'+ webNodeSetting.downloadPath + buildNodeSettings.publishFileNameForWeb + '" -y -o"' + webNodeSetting.publishPath + '"'
            echo '启动站点'
            bat 'C:\\Windows\\System32\\inetsrv\\appcmd.exe start site "' + webNodeSetting.webApplicationName+ '"'
        }
    },
    publishWeb2:{
        node(webNodeSetting2.node)
        {
            echo '发布web'
            echo '更新文件'
            echo '更新文件下载地址为：http://xxxx/' + buildNodeSettings.publishFileNameForWeb
            echo '下载文件'
            bat env.oss + ' -c ' + env.ossconfig + ' cp  "oss://xxxx/' + buildNodeSettings.publishFileNameForWeb + '" ' + webNodeSetting2.downloadPath
            echo '文件下载完成'
            echo '停止站点'
            bat 'C:\\Windows\\System32\\inetsrv\\appcmd.exe stop site "' + webNodeSetting2.webApplicationName + '"'
            bat '"' + env.zip + '" x "'+ webNodeSetting2.downloadPath + buildNodeSettings.publishFileNameForWeb + '" -y -o"' + webNodeSetting2.publishPath + '"'
            echo '启动站点'
            bat 'C:\\Windows\\System32\\inetsrv\\appcmd.exe start site "' + webNodeSetting2.webApplicationName+ '"'
        }
    },
    publishWeb3:{
        node(webNodeSetting3.node)
        {
            withEnv(['oss=C:\\Tools\\oss\\ossutil.exe', 'ossconfig=C:\\Tools\\oss\\config']) {//需要手动设置变量
                echo '发布web'
                echo '更新文件'
                echo '更新文件下载地址为：http://xxxx/' + buildNodeSettings.publishFileNameForWeb
                echo '下载文件'
                bat env.oss + ' -c ' + env.ossconfig + ' cp  "oss://xxxx/' + buildNodeSettings.publishFileNameForWeb + '" ' + webNodeSetting3.downloadPath
                echo '文件下载完成'
                echo '停止站点'
                bat 'C:\\Windows\\System32\\inetsrv\\appcmd.exe stop site "' + webNodeSetting3.webApplicationName + '"'
                bat '"' + env.zip + '" x "'+ webNodeSetting3.downloadPath + buildNodeSettings.publishFileNameForWeb + '" -y -o"' + webNodeSetting3.publishPath + '"'
                echo '启动站点'
                bat 'C:\\Windows\\System32\\inetsrv\\appcmd.exe start site "' + webNodeSetting3.webApplicationName+ '"'
            }
        }
    }
}

stage('Publish Service')
{
    parallel publishService1:
    {
        node(serviceNodeSetting.node){
            
            //发布windows service
            echo '发布Service'
            echo '下载文件'
            bat env.oss + ' -c ' + env.ossconfig + ' cp  "oss://xxxx/' + buildNodeSettings.publishFileNameForService + '" ' + serviceNodeSetting.downloadPath
            echo '卸载Windows Services'
            try{
                bat 'net stop ' + serviceNodeSetting.serviceName
                bat env.InstallUtil + ' -u ' + serviceNodeSetting.serviceName
            }catch(ex)
            {
                echo '卸载失败：' + ex
                try{
                    bat 'sc delete ' + serviceNodeSetting.serviceName
                }catch(ex2)
                {
                    echo '强制删除失败：' +ex2
                }
            }
            echo '解压文件'
            bat '"' + env.zip + '" x "'+ serviceNodeSetting.downloadPath + buildNodeSettings.publishFileNameForService + '" -y -o"' + serviceNodeSetting.publishPath + '"'
            echo '服务安装'
            bat env.InstallUtil + ' ' + serviceNodeSetting.publishPath + '\\' + serviceNodeSetting.serviceFileName + ' /name='+ serviceNodeSetting.serviceName + ' /display=' + serviceNodeSetting.serviceName + ' /desc=' + serviceNodeSetting.serviceName
            echo '启动服务'
            bat 'net start ' + serviceNodeSetting.serviceName
            
        }
    },
    publishService2:
    {
        node(serviceNodeSetting2.node){
            
            //发布windows service
            echo '发布Service'
            echo '下载文件'
            bat env.oss + ' -c ' + env.ossconfig + ' cp  "oss://xxxx/' + buildNodeSettings.publishFileNameForService + '" ' + serviceNodeSetting2.downloadPath
            echo '卸载Windows Services'
            try{
                bat 'net stop ' + serviceNodeSetting2.serviceName
                bat env.InstallUtil + ' -u ' + serviceNodeSetting2.serviceName
            }catch(ex)
            {
                echo '卸载失败：' + ex
                try{
                    bat 'sc delete ' + serviceNodeSetting2.serviceName
                }catch(ex2)
                {
                    echo '强制删除失败：' +ex2
                }
            }
            echo '解压文件'
            bat '"' + env.zip + '" x "'+ serviceNodeSetting2.downloadPath + buildNodeSettings.publishFileNameForService + '" -y -o"' + serviceNodeSetting2.publishPath + '"'
            echo '服务安装'
            bat env.InstallUtil + ' ' + serviceNodeSetting2.publishPath + '\\' + serviceNodeSetting2.serviceFileName + ' /name='+ serviceNodeSetting2.serviceName + ' /display=' + serviceNodeSetting2.serviceName + ' /desc=' + serviceNodeSetting2.serviceName
            echo '启动服务'
            bat 'net start ' + serviceNodeSetting2.serviceName
            
        }
    },
    publishService3:
    {
        node(serviceNodeSetting3.node){
            withEnv(['oss=C:\\Tools\\oss\\ossutil.exe', 'ossconfig=C:\\Tools\\oss\\config']) {//需要手动设置变量
                //发布windows service
                echo '发布Service'
                echo '下载文件'
                bat env.oss + ' -c ' + env.ossconfig + ' cp  "oss://xxxx/' + buildNodeSettings.publishFileNameForService + '" ' + serviceNodeSetting3.downloadPath
                echo '卸载Windows Services'
                try{
                    bat 'net stop ' + serviceNodeSetting3.serviceName
                    bat env.InstallUtil + ' -u ' + serviceNodeSetting3.serviceName
                }catch(ex)
                {
                    echo '卸载失败：' + ex
                    try{
                        bat 'sc delete ' + serviceNodeSetting3.serviceName
                    }catch(ex2)
                    {
                        echo '强制删除失败：' +ex2
                    }
                }
                echo '解压文件'
                bat '"' + env.zip + '" x "'+ serviceNodeSetting3.downloadPath + buildNodeSettings.publishFileNameForService + '" -y -o"' + serviceNodeSetting3.publishPath + '"'
                echo '服务安装'
                bat env.InstallUtil + ' ' + serviceNodeSetting3.publishPath + '\\' + serviceNodeSetting3.serviceFileName + ' /name='+ serviceNodeSetting3.serviceName + ' /display=' + serviceNodeSetting3.serviceName + ' /desc=' + serviceNodeSetting3.serviceName
                echo '启动服务'
                bat 'net start ' + serviceNodeSetting3.serviceName
            }
        }
    }
}