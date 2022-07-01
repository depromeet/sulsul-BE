<div align="center">

<p align="center">
    
<img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" />
<img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">
<img src="https://img.shields.io/badge/Spring security%20-%236DB33F.svg?&style=for-the-badge&logo=spring security&logoColor=white"/>
<img src="https://img.shields.io/badge/Spring boot-6DB33F?style=for-the-badge&logo=spring boot&logoColor=white">
</p>

<p align="center">
<img src="https://img.shields.io/badge/hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white">
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
<img src="https://img.shields.io/badge/amazonaws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white"> 
<img src="https://img.shields.io/badge/git-F05032.svg?&style=for-the-badge&logo=git&logoColor=white"/>
<img src="https://img.shields.io/badge/gitHub-181717?style=for-the-badge&logo=gitHub&logoColor=white">
<img src="https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=Notion&logoColor=white">
    
</p>


# BEERAiR

![그래픽 이미지_(1024x500)](https://user-images.githubusercontent.com/44468282/176907642-5b9acea2-4e71-4bbc-b643-2d7660314fc4.png)

### 🍺 BEER:AiR ✈️ 방구석 맥주 소믈리에들을 위한 맥주 기록 서비스

> 비어에어를 이용해서 나만의 맥주도감을 만들어보세요!
> 그 때  마셨던 맛있는 맥주가 뭐였지?🤔
> 더는 편의점 맥주 진열대 앞에서 고민하지 마세요.

[비어에어와 함께 세계여행✈️ 떠나볼까요? 탑승하세요!](https://beerair.kr/)

<div align="center">
    <a href='https://play.google.com/store/apps/details?id=com.sulsul'>
        <img alt='다운로드하기 Google Play' width='200px' src='https://play.google.com/intl/en_us/badges/static/images/badges/ko_badge_web_generic.png'/>
    </a>
</div>

</div>

# 📚 API Docs
* [API Description Link](https://github.com/depromeet/sulsul-BE/wiki/APIs)


# 🚧 파이프라인
![image](https://user-images.githubusercontent.com/44468282/175941387-f46fab1d-db6f-4d07-8e07-2ece48d65778.png)

Git에서 시작돼서 서버 배포까지 Git으로 끝나는 GitOps 패턴으로 파이프라인을 구현했습니다.
feature 브랜치가 merge되어 prod(운영서버), dev(개발서버) 브랜치에 반영될 때만 트리거 발생하여 각 환경에 맞게 배포가 진행됩니다.

#### 배포 정책

이미지 태깅 정책은 pr 번호로, 배포 성공시 슬랙 알람에도 어떤 이미지가 배포되었는지 확인할 수 있습니다. 슬랙 메세지를 통해 배포 이력을 관리할 수 있어서 장애시에도 당황하지 않고 간단하게 애플리케이션 롤백을 할 수 있습니다. 


#### Github Action 최대로 활용하기
![image](https://user-images.githubusercontent.com/44468282/176908283-d1a7e2e8-8128-42a4-9259-37fc68213c1f.png)
관리 포인트를 줄이기 위해 Github Action으로만 파이프라인을 구축했어요.
그만큼 호스팅 서버의 부하를 줄이기 위해 도커 스테이징 빌드, 도커 캐싱 등을 사용하였습니다.

더 자세한 내용이 궁금하시다면 맥주 한 캔과 함께 파이프라인을 여행해봅시다.
[파이프라인을 여행하는 개발자를 위한 안내서](https://www.notion.so/king-zeze/637b17ef6ed6494e9aa55fdadc83b7d1)

# 🖥 모니터링
![image](https://user-images.githubusercontent.com/44468282/176908545-3556e522-b2d8-40ca-a319-112a29661cf9.png)
뉴렐릭을 통한 성능 모니터링과 센트리를 통해 에러 모니터링을 진행했습니다.


# 📂 Database Modeling

![](https://i.imgur.com/nq8L8bt.png)

# 📕 Beer-Air는 이러한 기능들을 담고 있어요

![image](https://user-images.githubusercontent.com/44468282/176907265-a85b736f-77ee-4d7c-b6ae-b3769bc49eae.png)



# ⚡️팀 협업 방식
**K-애자일의 정수** 🤼

* [🍻Commit Convertion](https://github.com/depromeet/sulsul-BE/wiki/%F0%9F%8D%BBCommit-Convertion)
* [🍻Code Convention](https://github.com/depromeet/sulsul-BE/wiki/%F0%9F%8D%BBCode-Convention)
* [🍻Agile strategy](https://github.com/depromeet/sulsul-BE/wiki/%F0%9F%8D%BBAgile-strategy)


# ❤️ 회고
**기억보단 기록을** 📑

* [전체 join에서 LAZY전략을 사용한 이유](https://hello-backend.tistory.com/165)
* [batch_size를 적용해 보며](https://hello-backend.tistory.com/167)
* [시큐어 코딩과 필터 적용(JSON)](https://hello-backend.tistory.com/168)
* [주니어 개발자 상반기 회고 (서비스 기업 이직 후기)](https://minsoolog.tistory.com/58)
* [슬기로운 nginx 설정 가이드](https://king-zeze.notion.site/nginx-1763c7fc2dd640f193fac2984ad290ab)
* [Github Action 트러블슛 로그](https://king-zeze.notion.site/Github-Action-4db29adcebea41c69cbb3984987e9c93)




# 🤼 Back-End Contributers
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


# 📸 8조
![image](https://user-images.githubusercontent.com/44468282/176905905-bd24882e-36ad-4410-b9cd-8379bda0f89a.png)

8조를 빛내준 팀원분들과 디프만 분들께 감사의 인사를 드립니다☺️
