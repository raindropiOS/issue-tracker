## 🧀 프로젝트 요약
* 깃허브의 이슈 탭과 유사한 이슈 관리 앱
* 개발기간 : 2023/05 - 2023/06

## 🧀 개발 내용
* 무한 스크롤 기능
* 이슈 생성 기능
* 이슈 필터 기능

## 🧀 스크린샷
<p align="center">
    <img src="https://github.com/raindropiOS/issue-tracker/assets/70703326/39f05389-cbee-477e-8b89-94035a797f4a" align="center" width="30%">
  <img src="https://github.com/raindropiOS/issue-tracker/assets/70703326/0079151f-bf76-451a-84df-c98401e4cee5" align="center" width="30%">
    <img src="https://github.com/raindropiOS/issue-tracker/assets/70703326/feb75a40-e141-4eb6-a766-fcff437d1629" align="center" width="30%">
</p>

<p align="center">
  <img src="https://github.com/raindropiOS/issue-tracker/assets/70703326/829243fd-1295-4cbc-a4f2-f69b190fd7a8" align="center" width="30%">
    <img src="https://github.com/raindropiOS/issue-tracker/assets/70703326/d398574d-385f-486c-9645-a958ce7a63ac" align="center" width="30%">
    <img src="https://github.com/raindropiOS/issue-tracker/assets/70703326/4ab96ce2-1113-42a5-88f3-85612d392e60" align="center" width="30%">
</p>

### 무한 스크롤 기능
```swift
if collectionViewDelegate?.networkManager.networkingState == .fetchingDataEnded {
    collectionViewDelegate?.fetchData()
}
```
데이터를 가져오는 중에는, 사용자가 여러번 스크롤하더라도 한 번만 가져오도록 상태를 정의함.  

### 이슈 생성 기능
```swift
func postIssue(with issue: PostIssueFormat) {
        let urlString = Server.additionalInfo.rawValue
        guard var request = urlRequestFactory.makeUrlRequest(urlString) else {
            logger.log("Making URLRequest is failed")
            return
        }
        request.httpMethod = "POST"
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        guard let data = issueDataEncoder.encodeIssueDatum(with: issue) else { return }
        let jsonData = try? JSONSerialization.jsonObject(with: data, options: .allowFragments) as? Data ?? nil
        
        request.httpBody = data
        
        networkingState = .fetchingDataInProgress
        URLSession.shared.dataTask(with: request) { (_, response, error) in
            if let response = response {
                print("response : \(response)")
            }
            
            if let error = error {
                print("error : \(error)")
            }
            self.networkingState = .fetchingDataEnded
        }.resume()
    }
```
객체를 받아 이슈를 추가하도록 작성.

```swift
@IBAction func saveButtonTouched(_ sender: Any) {
    issue.title = titleInputField.text ?? "no title input"
    issue.contents = contentsInput.text
    networkManager.postIssue(with: issue)
    navigationController?.popViewController(animated: true)
}
```
저장 버튼을 터치하면 `postIssue`로 이슈 정보를 전달.

### 이슈 필터 기능
```swift
func queryIssueData(
    with query: [String: String],
    completion: @escaping (Result<IssueFrameHolder, Error>) -> Void) {
    guard var urlComponents = URLComponents(string: baseUrlString) else {
        logger.log("queryIssueData : URLComponents 생성 실패")
        return
    }
        
    var query = query
    query["cntPage"] = "\(self.cntPage)"
        
    let queryItems = query.map { URLQueryItem(name: $0.key, value: $0.value) }
    urlComponents.queryItems = queryItems
    guard let url = urlComponents.url else {
        logger.log("queryIssueData : URL 생성 실패")
        return
    }
    let request = URLRequest(url: url, timeoutInterval: 30.0)
    self.networkingState = .fetchingDataInProgress
    URLSession.shared.dataTask(with: request) { (data, _, error) in
        if let error = error {
            completion(.failure(error))
        }
        
        if let data = data {
            do {
                let issueFrames = try self.issueDataDecoder.decodeIssueData(data)
                self.cntPage += 1
                completion(.success(issueFrames))
            } catch let decodeError {
                completion(.failure(decodeError))
            }
        }
        self.networkingState = .fetchingDataEnded
    }.resume()
}
```
네트워크 매니저가 `cntPage`로 페이지 정보를 가지고 있는 상태이다.  
뷰 컨트롤러가 페이지 정보를 가지고 있었으면 객체의 책임이 더 적절했을 것 같다.  

## 🧀 팀 멤버
<div align="center">
  <img width="575" alt="" src="https://github.com/sjuhan123/issue-tracker/assets/88966578/e627eba1-5d3a-457a-a356-db53030739b1">
</div>

