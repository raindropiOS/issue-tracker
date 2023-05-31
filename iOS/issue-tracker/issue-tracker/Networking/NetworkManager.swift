//
//  NetworkManager.swift
//  issue-tracker
//
//  Created by 에디 on 2023/05/20.
//

import Foundation
import OSLog

class NetworkManager {
    let logger = Logger()
    let urlRequestFactory: URLRequestProducible = URLRequestFactory()
    let issueDataDecoder: IssueDataDecodable = IssueDataDecoder()
    let baseUrlString: String = Server.base.rawValue
    
    func fetchIssueData(
        with urlString: String,
        completion: @escaping (Result<IssueFrameHolder, Error>) -> Void) {
            guard let request = urlRequestFactory.makeUrlRequest(urlString) else {
                logger.log("Making URLRequest is failed")
                return
            }
            URLSession.shared.dataTask(with: request) { (data, _, error) in
                if let error = error {
                    completion(.failure(error))
                }
                
                if let data = data {
                    do {
                        let issueFrames = try self.issueDataDecoder.decodeIssueData(data)
                        completion(.success(issueFrames))
                    } catch let decodeError {
                        completion(.failure(decodeError))
                    }
                }
            }.resume()
        }
    
    func queryIssueData(
        with query: [String: String],
        completion: @escaping (Result<IssueFrameHolder, Error>) -> Void)
    {
        guard var urlComponents = URLComponents(string: baseUrlString) else {
            logger.log("queryIssueData : URLComponents 생성 실패")
            return
        }
        let queryItems = query.map { URLQueryItem(name: $0.key, value: $0.value) }
        urlComponents.queryItems = queryItems
        guard let url = urlComponents.url else {
            logger.log("queryIssueData : URL 생성 실패")
            return
        }
        #warning("print문 삭제")
        print(url)
        let request = URLRequest(url: url, timeoutInterval: 30.0)
        URLSession.shared.dataTask(with: request) { (data, _, error) in
            if let error = error {
                completion(.failure(error))
            }
            
            if let data = data {
                do {
                    let issueFrames = try self.issueDataDecoder.decodeIssueData(data)
                    completion(.success(issueFrames))
                } catch let decodeError {
                    completion(.failure(decodeError))
                }
            }
        }.resume()
    }
}
