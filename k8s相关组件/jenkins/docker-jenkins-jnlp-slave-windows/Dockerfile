FROM mcr.microsoft.com/windows/servercore:latest

#安装Msbuild
SHELL ["cmd", "/S", "/C"]

ADD https://aka.ms/vs/15/release/vs_buildtools.exe C:\\Downloads\\vs_buildtools.exe
ADD https://dist.nuget.org/win-x86-commandline/v4.3.0/nuget.exe C:\\Nuget\\nuget.exe

RUN C:\\Downloads\\vs_buildtools.exe --add Microsoft.VisualStudio.Workload.MSBuildTools --add Microsoft.VisualStudio.Workload.NetCoreBuildTools --add Microsoft.VisualStudio.Workload.VCTools --add Microsoft.VisualStudio.Workload.WebBuildTools --quiet --wait
RUN SETX /M Path "%Path%;C:\\Nuget;C:\\Program Files (x86)\\Microsoft Visual Studio\\2017\\BuildTools\\MSBuild\\15.0\\Bin"

#安装jenkins-slave
RUN powershell (new-object System.Net.WebClient).Downloadfile('https://github.com/frekele/oracle-java/releases/download/8u212-b10/jdk-8u212-windows-x64.exe', 'C:\\Downloads\\jdk-8u211-windows-x64.exe')
RUN powershell start-process -filepath C:\\Downloads\\jdk-8u211-windows-x64.exe -passthru -wait -argumentlist "/s,INSTALLDIR=c:\\Java\\jdk1.8.0_212\\,/L,install64.log"
RUN dir c:\\ \ 
RUN del C:\\jdk-8u211-windows-x64.exe

SHELL ["powershell"]
ARG BASE_URL

RUN (New-Object System.Net.WebClient).DownloadFile('{0}/jnlpJars/slave.jar' -f $env:BASE_URL, 'slave.jar') ;

ENTRYPOINT ["c:\\Java\\jdk1.8.0_212\\bin\\java.exe", "-jar", ".\\slave.jar"]