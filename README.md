
# BEERAiR
![그래픽 이미지_(1024x500)](https://user-images.githubusercontent.com/44468282/175938054-8cb03cb0-b6ca-4219-ad5a-e5f924cff499.png)
🍺 BEER:AiR ✈️ 방구석 맥주 소믈리에들을 위한 맥주 기록 서비스

비어에어를 이용해서 나만의 맥주도감을 만들어보세요!
그 때  마셨던 맛있는 맥주가 뭐였지?🤔
더는 편의점 맥주 진열대 앞에서 고민하지 마세요.
[비어에어와 함께 세계여행✈️ 떠나볼까요?](https://beerair.kr/)


--
# 🛠  기술스택

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" />
<img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Spring security%20-%236DB33F.svg?&style=for-the-badge&logo=spring security&logoColor=white"/>
<img src="https://img.shields.io/badge/Spring boot-6DB33F?style=for-the-badge&logo=spring boot&logoColor=white">
</p>
<p align="center"> <img src="https://img.shields.io/badge/hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white">
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
<img src="https://img.shields.io/badge/amazonaws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white"> 

<p align="center">
  <img src="https://img.shields.io/badge/git-F05032.svg?&style=for-the-badge&logo=git&logoColor=white"/>
<img src="https://img.shields.io/badge/gitHub-181717?style=for-the-badge&logo=gitHub&logoColor=white">
<!-- <img src="https://img.shields.io/badge/jenkins-D24939?style=for-the-badge&logo=jenkins&logoColor=white"> -->
<img src="https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=Notion&logoColor=white">
</p>



# 🚧  파이프라인
![image](https://user-images.githubusercontent.com/44468282/175941387-f46fab1d-db6f-4d07-8e07-2ece48d65778.png)

Git에서 시작돼서 서버 배포까지 Git으로 끝나는 GitOps 패턴으로 파이프라인을 구현했습니다.
feature 브랜치가 merge되어 prod(운영서버), dev(개발서버) 브랜치에 반영될 때만 트리거 발생하여 각 환경에 맞게 배포가 진행됩니다.

#### 배포 정책

이미지 태깅 정책은 pr 번호로, 배포 성공시 슬랙 알람에도 어떤 이미지가 배포되었는지 확인할 수 있습니다. 슬랙 메세지를 통해 배포 이력을 관리할 수 있어서 장애시에도 당황하지 않고 간단하게 애플리케이션 롤백을 할 수 있습니다. 


#### Github Action 최대로 활용하기
관리 포인트를 줄이기 위해 Github Action으로만 파이프라인을 구축했어요.
그만큼 호스팅 서버의 부하를 줄이기 위해 도커 스테이징 빌드, 도커 캐싱 등을 사용하였습니다.

더 자세한 내용이 궁금하시다면 맥주 한 캔과 함께 파이프라인을 여행해봅시다.
[파이프라인을 여행하는 개발자를 위한 안내서](https://www.notion.so/king-zeze/637b17ef6ed6494e9aa55fdadc83b7d1)

# 


# 📂 Database Modeling

![](https://i.imgur.com/nq8L8bt.png)

# 🤼 Contributers
<table>

<tr>
<td align="center"><a target="_blank" rel="noopener noreferrer" href="https://camo.githubusercontent.com/38bf90684a8e9f04c5371646dc946e06e74c5b8e0658bab5d4c372db42c4e212/68747470733a2f2f696d616765732e7765736572762e6e6c2f3f75726c3d68747470733a2f2f617661746172732e67697468756275736572636f6e74656e742e636f6d2f752f35393838383638343f763d343f763d3426683d32353026773d323530266669743d636f766572266d61736b3d636972636c65266d61786167653d3764"><img src="https://camo.githubusercontent.com/38bf90684a8e9f04c5371646dc946e06e74c5b8e0658bab5d4c372db42c4e212/68747470733a2f2f696d616765732e7765736572762e6e6c2f3f75726c3d68747470733a2f2f617661746172732e67697468756275736572636f6e74656e742e636f6d2f752f35393838383638343f763d343f763d3426683d32353026773d323530266669743d636f766572266d61736b3d636972636c65266d61786167653d3764" alt="Ting-Kim" data-canonical-src="https://images.weserv.nl/?url=https://avatars.githubusercontent.com/u/59888684?v=4?v=4&amp;h=250&amp;w=250&amp;fit=cover&amp;mask=circle&amp;maxage=7d" style="max-width: 100%;"></a></td>
<td align="center"><a target="_blank" rel="noopener noreferrer" href="https://camo.githubusercontent.com/b819c9601ba2028573addd25d3fd36775d47bd47d56e71cdf6e8ad0ddd7e5ad2/68747470733a2f2f696d616765732e7765736572762e6e6c2f3f75726c3d68747470733a2f2f617661746172732e67697468756275736572636f6e74656e742e636f6d2f752f35333734343336333f763d343f763d3426683d32353026773d323530266669743d636f766572266d61736b3d636972636c65266d61786167653d3764"><img src="https://camo.githubusercontent.com/b819c9601ba2028573addd25d3fd36775d47bd47d56e71cdf6e8ad0ddd7e5ad2/68747470733a2f2f696d616765732e7765736572762e6e6c2f3f75726c3d68747470733a2f2f617661746172732e67697468756275736572636f6e74656e742e636f6d2f752f35333734343336333f763d343f763d3426683d32353026773d323530266669743d636f766572266d61736b3d636972636c65266d61786167653d3764" alt="RyooChan" data-canonical-src="https://images.weserv.nl/?url=https://avatars.githubusercontent.com/u/53744363?v=4?v=4&amp;h=250&amp;w=250&amp;fit=cover&amp;mask=circle&amp;maxage=7d" style="max-width: 100%;"></a></td>
<td align="center"><a target="_blank" rel="noopener noreferrer" href="https://camo.githubusercontent.com/7979eb8ee2e22c9d89d55298489da71088dc8d8dc72b49a2645f0b1d9a51f84c/68747470733a2f2f696d616765732e7765736572762e6e6c2f3f75726c3d68747470733a2f2f617661746172732e67697468756275736572636f6e74656e742e636f6d2f752f37323638353037303f763d343f763d3426683d32353026773d323530266669743d636f766572266d61736b3d636972636c65266d61786167653d3764"><img src="https://camo.githubusercontent.com/aafb49de5bf71f12115bda5066c4a79bdc1429c85278fcd58b15d45097d2e9a3/68747470733a2f2f696d616765732e7765736572762e6e6c2f3f75726c3d68747470733a2f2f617661746172732e67697468756275736572636f6e74656e742e636f6d2f752f35323039353934353f763d343f763d3426683d32353026773d323530266669743d636f766572266d61736b3d636972636c65266d61786167653d3764" alt="minsoozz" data-canonical-src="https://images.weserv.nl/?url=https://avatars.githubusercontent.com/u/52095945?v=4?v=4&amp;h=250&amp;w=250&amp;fit=cover&amp;mask=circle&amp;maxage=7d" style="max-width: 100%;"></a></td>
<td align="center"><a target="_blank" rel="noopener noreferrer" href="https://camo.githubusercontent.com/25588e3b2c43c8a605a1e10e371572463fbd49f0e96244ddda31b91a4ac66a30/68747470733a2f2f696d616765732e7765736572762e6e6c2f3f75726c3d68747470733a2f2f617661746172732e67697468756275736572636f6e74656e742e636f6d2f752f32383439393535303f763d343f763d3426683d32353026773d323530266669743d636f766572266d61736b3d636972636c65266d61786167653d3764"><img src="https://camo.githubusercontent.com/2d1430fab9c35725bddab9bb436d004ed65d5fa539bc9e68a8ca2b1497144224/68747470733a2f2f696d616765732e7765736572762e6e6c2f3f75726c3d68747470733a2f2f617661746172732e67697468756275736572636f6e74656e742e636f6d2f752f34343436383238323f763d343f763d3426683d32353026773d323530266669743d636f766572266d61736b3d636972636c65266d61786167653d3764" alt="zeze1004" data-canonical-src="https://images.weserv.nl/?url=https://avatars.githubusercontent.com/u/44468282?v=4?v=4&amp;h=250&amp;w=250&amp;fit=cover&amp;mask=circle&amp;maxage=7d" style="max-width: 100%;"></a></td>
</tr>
    <tr>
    <td align="center"><a href="https://github.com/Ting-Kim">김태호</a></td>
<td align="center"><a href="https://github.com/RyooChan">류찬</a></td>
<td align="center"><a href="https://github.com/minsoozz">김민수</a></td>
<td align="center"><a href="https://github.com/zeze1004">김소정</a></td>
</table>





# ⚡️팀 협업 방식

* **Wiki로 옮긴 뒤에 링크 달게욥**

🍻Commit Convertion

## 커밋 메시지

커밋 메시지는 title, body, footer 로 구분한다

```
type: Subject

body

footer

```

제목은 메시지의 유형과 제목으로 구성한다

### 유형

유형은 제목에 포함되며 다음 유형으로 구성한다

`feat` : 새로운 기능을 추가할 경우

`fix` : 버그를 포함한 다소 급한 수정이 있을 경우

`docs` : 문서 변경 사항이 있을 경우

`style` : 코드 포맷 변경, 세미콜론 누락 등 코드의 변경이 없는 경우

`refactor` : 새로운 기능이나 버그 수정 없이 기존 production 코드 리팩토링을 하는 경우

`test` : 테스트 추가, 테스트 코드 리팩토링, production 코드의 변경이 없는 경우

`chore` : 빌드 작업, 패키지 구성 등 production 코드의 변경이 없는 경우

`rename` : 파일 혹은 폴더명을 수정하거나 옮기는 작업만 진행한

`remove` : 파일 삭제 작업만을 수행한 경우

### The Subject

제목은 50자 이하로 제한하고 마침표로 끝나지 않아야 합니다. 커밋이 무엇을 했는지보다는 명령형을 사용하여 커밋이 하는 일을 설명하면 됩니다

제목의 첫 글자는 대문자로 합니다

- 깃모지 종류
    
    
    | 아이콘 | 코드 | 설명 |
    | --- | --- | --- |
    | 🎨 | :art: | 코드의 구조/형식을 개선합니다. |
    | ⚡️ | :zap: | 성능을 개선합니다. |
    | 🔥 | :fire: | 코드 또는 파일을 제거합니다. |
    | 🐛 | :bug: | 버그를 수정합니다. |
    | 🚑️ | :ambulance: | 중요한 핫픽스. |
    | ✨ | :sparkles: | 새로운 기능을 추가합니다. |
    | 📝 | :memo: | 문서를 추가하거나 업데이트합니다. |
    | 💄 | :lipstick: | UI 및 스타일 파일을 추가하거나 업데이트합니다. |
    | 🎉 | :tada: | 프로젝트를 시작합니다. |
    | ✅ | :white_check_mark: | 테스트를 추가, 업데이트 또는 통과합니다. |
    | 🔒️ | :lock: | 보안 문제를 수정합니다. |
    | 🔐 | :closed_lock_with_key: | 보안을 추가하거나 업데이트합니다. |
    | 🔖 | :bookmark: | 릴리스/버전 태그. |
    | ✏️ | :pencil2: | 오타를 수정합니다. |
    | 🔨 | :hammer: | 개발 스크립트를 추가하거나 업데이트합니다. |
    | 🔧 | :wrench: | 구성 파일을 추가하거나 업데이트합니다. |
    | 💩 | :poop: | 개선해야 할 나쁜 코드를 작성하십시오. |
    | 📦️ | :package: | 컴파일된 파일 또는 패키지를 추가하거나 업데이트합니다. |
    | 🔊 | :loud_sound: | 로그를 추가하거나 업데이트합니다. |
    | ⏪️ | :rewind: | 변경 사항을 되돌립니다. |
    | 🔀 | :twisted_rightwards_arrows: | 분기를 병합합니다. |

### The Body

내용은 선택 사항이며 커밋에 설명이 필요한 경우 작성합니다. 어떻게가 아니라 무엇을, 왜 커밋했는지 작성하면 됩니다. 내용을 작성할 때 제목과 내용 사이에는 빈 줄이 있어야 하며 내용의 길이는 72자 이내로 제한합니다

- 한 줄당 72자 내로 작성하고, 본문 내용은 양에 구애받지 않고 최대한 상세히 작성합니다. 
어떻게x 무엇을, 왜 변경했는지 위주로 설명합니다.
    
    

### The Footer

바닥글은 선택 사항이며 issue tracker ID를 참조하는데 사용됩니다

ex )  issue : #51

### 레퍼런스 문서

[Udacity Nanodegree Style Guide](https://udacity.github.io/git-styleguide/)

🍻Code Convention

## 클래스

---

- 클래스의 이름은 명사이어야 하며, 각 단어의 첫 글자는 `대문자` 로 시작합니다.
- 완전한 단어를 사용하고 약어는 지양합니다.

## 메소드

---

- 메소드의 이름은 동사이어야 하며, `camelCase` 표기법을 준수합니다.
- 반환형이 `boolean` 일 경우 메소드의 이름의 시작은 `is-` 로 통일합니다.

## 변수

---

- 변수의 이름은 `camelCase` 표기법을 준수합니다.
- 완전한 단어를 사용하고 약어는 지양합니다.
- 변수의 이름은 `사용 의도를 알아낼 수 있도록 의미적`으로  작성합니다.

## 상수

---

- 상수는 대문자로 작성하고, 각각의 단어는 `_` 로 구분합니다.
- 연관된 상수들의 집합이 있다면 `Enum Class`로 관리합니다.

## 초기화

---

- `객체 & 변수` 의 초기화는 클래스와 메소드의 시작에 위치합니다.

## 주석

---

주석으로 의도를 표현하는 것보다 코드로 의도를 표현하는 것을 지향합니다

### TODO 주석

- `앞으로 할 일` 또는 `당장 구현하기 어려운 업무` 를 작성합니다.
- TODO 주석은 Intellij 하단 메뉴의 `TODO` 탭을 지속적으로 확인하고,  점검합니다

![](https://i.imgur.com/IYvk94X.png)

## intellij-java-google-style.xml

---

이외 코드 컨벤션은 `intellij-java-google-style` 을 준수하고 있습니다.

🍻Agile strategy

디프만 11기에서 저희 팀은 Notion을 활용하여 협업을 진행하였습니다.
먼저 각 백엔드, 인프라, 프론트엔드, 그리고 디자인 팀 간 소통을 원할히 하기 위해 Kanban을 사용하였습니다.

![](https://i.imgur.com/S2K3oU0.png)

Kanban은 각 파트별로 시작 전, 진행 중, 완료 상태로 나누어 보여주고 해당하는 이슈에 대해 관리자를 할당하기 때문에 누가 어떤 이슈를 남겼는지, 어떤 상태를 처리중인지 쉽게 알 수 있어 보다 편리하고 생산성 높은 협업을 진행하도록 도와줍니다.

그리고 해당하는 이슈에 대해 Github에서 Issue를 생성하고, `feachers/~~` 등 역할에 맞는 브랜치를 만들어 Pull Request를 진행하였습니다.

![](https://i.imgur.com/pr6OFmr.png)

각각의 PR에 대해 Code review를 진행하였고, 2명 이상의 Approve를 받은 후에 merge가 가능하도록 하였습니다.

