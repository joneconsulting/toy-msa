## AWSCLI 설치 준비
$ aws --version

$ kubectl

$ curl -O https://s3.us-west-2.amazonaws.com/amazon-eks/1.29.6/2024-07-12/bin/linux/amd64/kubectl

$ chmod +x ./kubectl

$ mkdir -p $HOME/bin && cp ./kubectl $HOME/bin/kubectl && export PATH=$HOME/bin:$PATH

$ echo 'export PATH=$HOME/bin:$PATH' >> ~/.bashrc

$ kubectl version --client

$ aws sts get-caller-identity

$ aws configure

$ aws sts get-caller-identity

## EKS 클러스터 설치
$ curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" | tar xz -C /tm

$ sudo mv /tmp/eksctl /usr/local/bin

$ eksctl version

$ eksctl create cluster --name my-cluster --region ap-northeast-2 --fargate

## EKS 설정 업데이트
$ aws eks update-kubeconfig --region ap-northeast-2 --name my-cluster

## kubectl 실행
$ kubectl get no

## kubectl 오류 발생 (apiVersion 오류)
$ vi ~/.kube/config -> v1alpha1 -> v1beta1

## AWS CLI 업데이트
$ sudo yum remove awscli

$ curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"

$ unzip awscliv2.zip

$ sudo ./aws/install

