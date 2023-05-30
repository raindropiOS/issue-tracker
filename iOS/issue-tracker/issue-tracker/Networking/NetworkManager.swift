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
}
