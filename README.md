<image src='https://github.com/Beach-Combine/.github/blob/main/images/header.png?raw=true' width="800"/>
<br/>



<image src='https://github.com/Beach-Combine/.github/blob/main/images/longImage.png?raw=true' width="800"/>

<br/>

## 📽 Demo Video Link

[![BeachCombine](https://github.com/Beach-Combine/.github/blob/main/images/video.JPG?raw=true)](https://youtu.be/AJusc-HUwQA)

<br/>

## 🏛 Project Architecture

<image src='https://github.com/Beach-Combine/.github/blob/main/images/architecture.png?raw=true' width="800"/>

<br/>

## ✅ Server Deployment Process (CI/CD using Github Actions)

### Local : Gradle build, Docker build
1. jar build : `gradle build`
2. image creation : `docker build -t yourAccountName/repositoryName ./`
3. push to Docker Hub : `docker push yourAccountName/repositoryName`

(`AccoutName` and `RepositoryName` are from Docker Hub)

### Server : Deploy
1. Pull from Docker Hub : `docker pull yourAccountName/repositoryName`
2. Create image as configured in Docker-compose.yml : `docker tag yourAccountName/repositoryName dockerImageName`
3. Run Docker Compose : `docker-compose up`

(`dockerImageName` should be written as the image name in Docker-compose.yml)

<br />

## 🛠 Tech Stacks

<image src='https://github.com/Beach-Combine/.github/blob/main/images/techStack.png?raw=true' width="800"/>

<br/>

## 📁 ERD

<image src='https://github.com/Beach-Combine/.github/blob/main/images/erd.png?raw=true' width="800"/>

<br/>

## ❗ GIT Strategy

### 1) Git Workflow

### main → develop → feature/Issue#-feature, fix/Issue#-feature, refactor/Issue#-feature

1. Work individually on each branch `local - feature/Issue#-feature`
2. After completing the task, submit a PR to `remote - develop`.
3. After code review, receive approval and merge
4. Every time a merge occurs in `remote - develop`, all team members pull from `remote - develop` to maintain the latest status

### 2) Commit Convention

| Tag name | Description                                                 |
| -------- | ----------------------------------------------------------- |
| feat     | Commits that add a new feature                              |
| fix      | Commits that fix a bug                                      |
| hotfix   | Fix an urgent bug in issue or QA                            |
| build    | Commits that affect build components                        |
| chore    | Miscellaneous commits                                       |
| style    | Commits for code styling or format                          |
| docs     | Commits that affect documentation only                      |
| test     | Commits that add missing tests or correcting existing tests |
| refactor | Commits for code refactoring                                |

<br/>

## 📑 Coding Convention

### 1) Naming Convention

- Variables, functions, and class names should use camelCase.
- For functions, use a verb followed by a noun.
  e.g.) getInfo()
- Column names stored in the DB should use snake_case.
  e.g.) member_id
- URL names should use kebab-case, consisting of lowercase nouns.
- Use hyphens (-) as separators, and avoid using separators when possible.
  e.g.) **[www.example.com/user](http://www.example.com/user)**

### 2) Builder

- To improve readability, builders are required instead of constructors

<br/>

## 👥 Contributors

|                                    [권보민](https://github.com/pingowl)                                    |                                   [추서연](https://github.com/ChooSeoyeon)                                   |
| :---------------------------------------------------------------------------: | :--------------------------------------------------------------------------: |
| <img src="https://avatars.githubusercontent.com/u/101239440?v=4" width=150px> | <img src="https://avatars.githubusercontent.com/u/83302344?v=4" width=150px> |


<br/>

## 📎 Link

- Email : [t01053604256@gmail.com](mailto:t01053604256@gmail.com)
- [Mobile repository](https://github.com/Beach-Combine/Mobile)
- [Backend repository](https://github.com/Beach-Combine/Backend)
- [AI repository](https://github.com/Beach-Combine/AI)
  <br/>
