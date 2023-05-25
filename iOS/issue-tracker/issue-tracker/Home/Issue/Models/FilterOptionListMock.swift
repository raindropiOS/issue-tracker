//
//  FilterOptionListMock.swift
//  issue-tracker
//
//  Created by 에디 on 2023/05/24.
//

import Foundation

class FilterOptionListMock: FilterOptionsLike {
    var list: [[FilterOption]] = [
        [
            FilterOption(filterLabel: "열린 이슈", filterUrlStr: nil),
            FilterOption(filterLabel: "내가 작성한 이슈", filterUrlStr: nil),
            FilterOption(filterLabel: "내가 댓글을 남긴 이슈", filterUrlStr: nil),
            FilterOption(filterLabel: "닫힌 이슈", filterUrlStr: "{close=true}", isSelected: false)
        ],
        [
            FilterOption(filterLabel: "chloe", filterUrlStr: nil),
            FilterOption(filterLabel: "head", filterUrlStr: nil),
            FilterOption(filterLabel: "sam", filterUrlStr: nil),
            FilterOption(filterLabel: "zello", filterUrlStr: nil)
        ],
        [
            FilterOption(filterLabel: "레이블 없음", filterUrlStr: nil, isSelected: false),
            FilterOption(filterLabel: "그룹프로젝트:이슈트래커", filterUrlStr: nil, isSelected: false)
        ]
    ]
    
    func collectSelectedFilterOptionUrlString() -> String {
        var collectedOptionUrlString = "?"
        
        for options in list {
            for option in options {
                guard let urlStr = option.filterUrlStr else { continue }
                collectedOptionUrlString += option.isSelected ? urlStr : ""
            }
        }
        return collectedOptionUrlString.count == 1 ? "" : collectedOptionUrlString
    }
}
