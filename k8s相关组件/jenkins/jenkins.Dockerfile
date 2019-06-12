[
    {
        "Id": "sha256:ba607c18aeb76f625680a9786d00c9527aeeb9df971aa23346a404e9904d49aa",
        "RepoTags": [
            "jenkins/jenkins:lts"
        ],
        "RepoDigests": [
            "jenkins/jenkins@sha256:08bdd27b066e4ec6bfa1228876551e939df1aeaa9d878e6ddb2b183cd208dc2b"
        ],
        "Parent": "",
        "Comment": "",
        "Created": "2019-05-10T20:56:34.250509599Z",
        "Container": "",
        "ContainerConfig": {
            "Hostname": "",
            "Domainname": "",
            "User": "jenkins",
            "AttachStdin": false,
            "AttachStdout": false,
            "AttachStderr": false,
            "ExposedPorts": {
                "50000/tcp": {},
                "8080/tcp": {}
            },
            "Tty": false,
            "OpenStdin": false,
            "StdinOnce": false,
            "Env": [
                "PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin",
                "LANG=C.UTF-8",
                "JAVA_HOME=/docker-java-home",
                "JAVA_VERSION=8u212",
                "JAVA_DEBIAN_VERSION=8u212-b01-1~deb9u1",
                "JENKINS_HOME=/var/jenkins_home",
                "JENKINS_SLAVE_AGENT_PORT=50000",
                "JENKINS_VERSION=2.164.3",
                "JENKINS_UC=https://updates.jenkins.io",
                "JENKINS_UC_EXPERIMENTAL=https://updates.jenkins.io/experimental",
                "JENKINS_INCREMENTALS_REPO_MIRROR=https://repo.jenkins-ci.org/incrementals",
                "COPY_REFERENCE_FILE_LOG=/var/jenkins_home/copy_reference_file.log"
            ],
            "Cmd": [
                "/bin/sh",
                "-c",
                "#(nop) COPY file:466c2a26afc19254b24e27c9d86b6fa5ddabf266dc0b3cc898b0db83cecd8e41 in /usr/local/bin/install-plugins.sh "
            ],
            "ArgsEscaped": true,
            "Image": "sha256:5bb7d5351822147d18cba428e40616fcd54ef58272ba938aa295256f7da26f08",
            "Volumes": {
                "/var/jenkins_home": {}
            },
            "WorkingDir": "",
            "Entrypoint": [
                "/sbin/tini",
                "--",
                "/usr/local/bin/jenkins.sh"
            ],
            "OnBuild": null,
            "Labels": null
        },
        "DockerVersion": "18.09.6",
        "Author": "",
        "Config": {
            "Hostname": "",
            "Domainname": "",
            "User": "jenkins",
            "AttachStdin": false,
            "AttachStdout": false,
            "AttachStderr": false,
            "ExposedPorts": {
                "50000/tcp": {},
                "8080/tcp": {}
            },
            "Tty": false,
            "OpenStdin": false,
            "StdinOnce": false,
            "Env": [
                "PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin",
                "LANG=C.UTF-8",
                "JAVA_HOME=/docker-java-home",
                "JAVA_VERSION=8u212",
                "JAVA_DEBIAN_VERSION=8u212-b01-1~deb9u1",
                "JENKINS_HOME=/var/jenkins_home",
                "JENKINS_SLAVE_AGENT_PORT=50000",
                "JENKINS_VERSION=2.164.3",
                "JENKINS_UC=https://updates.jenkins.io",
                "JENKINS_UC_EXPERIMENTAL=https://updates.jenkins.io/experimental",
                "JENKINS_INCREMENTALS_REPO_MIRROR=https://repo.jenkins-ci.org/incrementals",
                "COPY_REFERENCE_FILE_LOG=/var/jenkins_home/copy_reference_file.log"
            ],
            "Cmd": null,
            "ArgsEscaped": true,
            "Image": "sha256:5bb7d5351822147d18cba428e40616fcd54ef58272ba938aa295256f7da26f08",
            "Volumes": {
                "/var/jenkins_home": {}
            },
            "WorkingDir": "",
            "Entrypoint": [
                "/sbin/tini",
                "--",
                "/usr/local/bin/jenkins.sh"
            ],
            "OnBuild": null,
            "Labels": null
        },
        "Architecture": "amd64",
        "Os": "linux",
        "Size": 703656764,
        "VirtualSize": 703656764,
        "GraphDriver": {
            "Data": {
                "LowerDir": "/var/lib/docker/overlay2/0caf2adce06314138f4a1544065dd1616f879d82dca0d1c26be639c9cd089c04/diff:/var/lib/docker/overlay2/c4852daab4a41615239cab98f7c5ab168d1a2f12e7954a4bc3270fd07638998c/diff:/var/lib/docker/overlay2/81f96c8c5c9ead18b25d16ddc5f750dcd54236ea94e117c45cd9e19ad226393c/diff:/var/lib/docker/overlay2/4380ec74d53fe4fee022c784b59e933d9fd9e1e898b10082ee73d3377a85a613/diff:/var/lib/docker/overlay2/10c71bc41a8d51e6fb6d62df435870fae60fb90398517b552681946de9497505/diff:/var/lib/docker/overlay2/499062d1a5379bf9fd434cc50b309180576e5c799823f668bacfac9021a919d3/diff:/var/lib/docker/overlay2/df732faaf9db9d22e6fb4e968e9d04678fea338aa8ceba8dd8cc1061e15ed7d0/diff:/var/lib/docker/overlay2/538be6cd284834f22bc73320c2d38ff4502a3a66a64f89f8c2c7b5aa1a3f357b/diff:/var/lib/docker/overlay2/1eff6cc027529598e1bdbad062a4b6ae95a61cc904ae9314b161ea2bfe65cb12/diff:/var/lib/docker/overlay2/775c0b516d7b3160cc9e002347a603cc94307e98ec3436a3ee3bb3629b588a32/diff:/var/lib/docker/overlay2/9f654ad68a1d275b1051eae90e1ba9aa38a81431b9804b6adae1d3edd49da90c/diff:/var/lib/docker/overlay2/d19c0300555f8a7e38ebdd2f136958cd7c46d0dd6b7d5a58901543f714432179/diff:/var/lib/docker/overlay2/837a56d8f79744184cf64db7007e98ca913fe6276e191b972081d0b25b3ea49f/diff:/var/lib/docker/overlay2/6149d2031880e3b28e925bd4be637f6bd88a65d148eb94ee1f82252a3c67e8a1/diff:/var/lib/docker/overlay2/a9e9e08d5c22e7679d767a6ae895c233edf4e0d40a99b1470f5c877a4c18ef81/diff:/var/lib/docker/overlay2/5b7908f396d107c455f9c67f417f15522820f14229992bf524f9cd52f002d0ed/diff:/var/lib/docker/overlay2/cac1e35f9764c9843576e70a862041ac53ed1277cb1f2d434372653a6cad1dc6/diff:/var/lib/docker/overlay2/aedf9059b8c78809ea49362d9335d3e74c22ffc4b25fbd1b4ec7854a6b2dd19a/diff",
                "MergedDir": "/var/lib/docker/overlay2/589628bb394b191e88ff62892a2d516a7fc399c306c8fb9da19624f9c560f749/merged",
                "UpperDir": "/var/lib/docker/overlay2/589628bb394b191e88ff62892a2d516a7fc399c306c8fb9da19624f9c560f749/diff",
                "WorkDir": "/var/lib/docker/overlay2/589628bb394b191e88ff62892a2d516a7fc399c306c8fb9da19624f9c560f749/work"
            },
            "Name": "overlay2"
        },
        "RootFS": {
            "Type": "layers",
            "Layers": [
                "sha256:f94641f1fe1f5c42c325652bf55f0513c881c86b620b912b15460e0bca07cc12",
                "sha256:ec62f19bb3aa1dcfacc9864be06f0af635c18021893d42598da1564beed97448",
                "sha256:2c719774c1e1c4b82c5b23bd40a7fc139aa5f0efddf7a969f72f8170c71dd058",
                "sha256:4230ff7f22885369f095f4414ef0ff1a94b93a6c93dc754b89ae14e470955ee7",
                "sha256:f0fa4eb58fbca60eb000893e29c5a7eff547cd0d9ae33851f165775872272db4",
                "sha256:23ee27cbbbfe3975df8bc6be651f2c2af80c76a4acb42ee94d678e41d0eeeb15",
                "sha256:5e16427687995486c1aba9ac54abbc35449168090b1a90b09d2ae1f7cf5eefea",
                "sha256:4f2388aa019a66c09b02f606e628368a201778e4cc1a14655000cee370971a58",
                "sha256:bf6cfd255a8cd411f06e5a8f98add4ae0a426e4beaaace2274aef07bd3f9143d",
                "sha256:def2659fecf17dceadda1c70bc2efe7c09a02b760d580eeee9887014ca8a9613",
                "sha256:40393cad35ed705e8ce835c9adf7b1e6945d2792dbdc5c3736724f6f950aa2dc",
                "sha256:b143155239a9d43cd082b9be00b0c5da8351041f1b4c1c2747559fa0b1105cd5",
                "sha256:47673974561b943a22c822ff9401c42ce6a059e88715388efbde99e4b93405da",
                "sha256:1e232f803e76a62b68c52c158655a2ce4ee6ac83fed622982f3a49f76256af8f",
                "sha256:867c923d9d4d020960c0baaff5cb2d7017ff0e400700d2db2cc4a41ee1f3151a",
                "sha256:1985825485160bca33739c9998b2e3733cccfea827a55c51f472b3f47c13f1ba",
                "sha256:9e7a4aa96e6daba12ead832951d93035d8df0e3aa6d0e0572bca191dc1e2d88f",
                "sha256:ced7347a48d1c9ac90ccd04223d35a2be0eb5687e31bcd0b388056d85d8e5c14",
                "sha256:582354b3823e26e3aff224cabb185051f108fcec056dfb9bb609bd3621e6355a"
            ]
        },
        "Metadata": {
            "LastTagTime": "0001-01-01T00:00:00Z"
        }
    }
]
