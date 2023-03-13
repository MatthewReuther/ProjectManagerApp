<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a name="readme-top"></a>

[![LinkedIn][linkedin-shield]][linkedin-url]

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/MatthewReuther/ProjectSyncUp">
    <img src="resources/images/logo.png" alt="Logo" width="80" height="80">
  </a>

<h1 align="center">ProjectSyncUp</h1>

  <p align="center">
    A web application, that helps streamline collaboration among team members working on projects together.
    <br />
    <a href="https://github.com/MatthewReuther/ProjectSyncUp"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://d35o9956qxz0nn.cloudfront.net/">View Deployed Application Site</a>
    <br />
    <a href="#usage">View Demo</a>
  </p>
</div>


<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>


<!-- ABOUT THE PROJECT -->
## About The Project

As the creator of ProjectSyncUp, my goal was to develop a simple project management application for my Nashville Software School capstone project. Having experienced numerous project management tools in my career as a web developer, I noticed that many of these tools could be too complicated and overwhelming for some teams. I believe that a tool with more features does not necessarily equate to a better experience. In fact, complicated tools can lead to confusion among users and team members, resulting in bottlenecks.

With this in mind, I set out to create a project management tool that would allow project managers to easily create projects, assign tasks to team members, and update project statuses and tasks. Additionally, project managers can view all their created projects in one place.

Some requirements for this project included:

* At least four API endpoints using the appropriate HTTP method for each "CRUD" operation
* User authentication with Amazon Cognito supporting multiple users
* Deployment to AWS using Sam
* An AWS serverless stack that includes: API Gateway, CloudFormation, CloudFront, CloudWatch, Cognito, DynamoDB, and Lambda
* Language Tools/Frameworks including: Dagger, Gradle, Checkstyle, JUnit, Mockito, and PlantUML

Overall, ProjectSyncUp was developed with the intention of helping teams stay focused on the important aspects of their projects. I take great pride in what I achieved with ProjectSyncUp and believe that it has the potential to become a valuable asset for project managers and their teams in the years ahead.

As a final note, I would like to point out that ProjectSyncUp is currently in beta 1, and there are more features that I plan to introduce in the future. For instance, team members will soon be able to track their time for specific tasks, and they will have the ability to view all assigned tasks. I am committed to continuously enhancing ProjectSyncUp to make it an even more powerful tool for project managers and their teams.

## Things I Learned

This project has been an invaluable experience that has helped me to solidify my understanding of the various tools and concepts that I learned during my 9-month Software Engineering course. One of the highlights of this project was the opportunity to create multiple API endpoints from scratch, using PlantUML class and sequence diagrams as part of the process. Additionally, I was able to learn how to work with AWS Cognito to create fake users, which has proven to be a valuable skill.

In addition to the technical skills I gained, I also learned the importance of debugging issues as they arose and the importance of unit testing. Through this project, I became more proficient in using tools like Docker and working with AWS Lambdas. Furthermore, I developed a stronger understanding of working with curl commands.

Overall, this project has played a vital role in developing my skills as a software engineer, boosting my confidence in both front-end and back-end development, and helping me to become a more well-rounded professional in the field.

<p align="right">(<a href="#readme-top">back to top</a>)</p>


### Built With

| Back-end                                                        | Front-end                                    |
|-----------------------------------------------------------------|----------------------------------------------|
| [![Java][Java]][Java-url]                                       | [![CSS][CSS]][CSS-url]                       |
| [![AWS Cognito][AWS Cognito]][Cognito-url]                      | [![HTML][HTML]][HTML-url]                    |
| [![Lambda][Lambda]][Lambda-url]                                 | [![JavaScript][JavaScript]][JavaScript-url]  | 
| [![AWS CloudFront][AWS CloudFront]][CloudFront-url]             | [![Bootstrap][Bootstrap.com]][Bootstrap-url] |
| [![DynamoDB][DynamoDB]][DynamoDB-url]                           |                                              |
| [![AWS CloudWatch][AWS CloudWatch]][CloudWatch-url]             |                                              |
| [![API Gateway][API Gateway]][Gateway-url]                      |                                              |
| [![AWS CloudFormation][AWS CloudFormation]][CloudFormation-url] |                                              |


<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.

### Prerequisites and Installation

1. Create or use an existing Amazon AWS account.
2. Install the latest version of AWS CLI. [Link to documentation](https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html)
3. Install the latest version of AWS SAM CLI. [Link to documentation](https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/install-sam-cli.html)
4. Install NodeJS before you can run some of the commands below (the `npm` ones).

- On Windows / WSL:
```shell
curl -fsSL https://deb.nodesource.com/setup_18.x | sudo -E bash - &&\
sudo apt-get install -y nodejs
```
- On macOS:
```shell
brew install node
```

### Run Locally

3. Run the Lambda service (aka the backend):
    - Build the Java code: `sam build`
    - Create an S3 bucket: `aws s3 mb s3://YOUR_BUCKET`
    - Deploy the SAM template: `sam deploy --s3-bucket BUCKET_FROM_ABOVE --parameter-overrides S3Bucket=BUCKET_FROM_ABOVE FrontendDeployment=local`
      > **NOTE:** _Yes you have to provide the same S3 bucket name twice. Yes this is annoying._
    - Run the local API: `sam local start-api --warm-containers LAZY`
4. Run a local web server (aka the frontend):
    - CD into the web directory: `cd web`
    - Install dependencies : `npm install`
    - Run the local server: `npm run run-local`
    -  > **NOTE:** Only songs found in the song table can be added to the playlist.

After doing all of this, you will have a server running on port `8000` - you can access it by going to [http://localhost:8000](http://localhost:8000) in your browser.

To stop either the local backend (the `sam local...` command) or local frontend (the `npm run...`) command, simply press `Ctrl-C` in the terminal where the process is running.



<!-- USAGE EXAMPLES -->
## Usage

[ProjectSyncUp Demo](https://drive.google.com/file/d/1itgVCMwvLUIxIf7khXwxNR4jaTPvPcuJ/view?usp=share_link)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- CONTACT -->
## Contact

Matthew Reuther - matthew.reuther@gmail.com

Project Link: [https://github.com/MatthewReuther/ProjectSyncUp](https://github.com/MatthewReuther/ProjectSyncUp)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/matthewreuther/
[DynamoDb]: https://img.shields.io/badge/AWS_DynamoDB-pink?style=for-the-badge&logo=amazondynamodb&logoColor=4053D6
[DynamoDb-url]: https://aws.amazon.com/dynamodb/
[AWS Cognito]: https://img.shields.io/badge/AWS_Cognito-green?style=for-the-badge
[Cognito-url]: https://aws.amazon.com/cognito/
[HTML]: https://img.shields.io/badge/HTML5-light?style=for-the-badge&logo=html5&logoColor=1572B6
[HTML-url]: https://en.wikipedia.org/wiki/HTML5#:~:text=HTML5%20is%20a%20markup%20language,as%20the%20HTML%20Living%20Standard.
[CSS]: https://img.shields.io/badge/CSS3-yellow?style=for-the-badge&logo=css3&logoColor=1572B6
[CSS-url]: https://www.w3.org/Style/CSS/Overview.en.html
[Lambda]: https://img.shields.io/badge/AWS_Lambda-white?style=for-the-badge&logo=awslambda&logoColor=FF9900
[Lambda-url]: https://aws.amazon.com/lambda/
[API Gateway]: https://img.shields.io/badge/AWS_API_Gateway-black?style=for-the-badge&logo=amazonapigateway&logoColor=FF4F8B
[Gateway-url]: https://aws.amazon.com/api-gateway/
[JavaScript]: https://img.shields.io/badge/JavaScript-20232A?style=for-the-badge&logo=javascript&logoColor=61DAFB
[JavaScript-url]: https://javascript.com/
[Bootstrap.com]: https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white
[Bootstrap-url]: https://getbootstrap.com
[Java]: https://img.shields.io/badge/Java-darkgreen?style=for-the-badge
[Java-url]: https://java.com/
[AWS CloudFront]: https://img.shields.io/badge/AWS_CloudFront-orange?style=for-the-badge
[Cloudfront-url]: https://aws.amazon.com/cloudfront/
[AWS CloudFormation]: https://img.shields.io/badge/AWS_CloudFormation-red?style=for-the-badge
[Cloudformation-url]: https://aws.amazon.com/cloudformation/
[AWS CloudWatch]: https://img.shields.io/badge/AWS_CloudWatch-beige?style=for-the-badge&logo=amazoncloudwatch&logoColor=FF4F8B
[Cloudwatch-url]: https://aws.amazon.com/cloudwatch/
