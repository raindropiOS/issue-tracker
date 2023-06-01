import styled, { createGlobalStyle } from 'styled-components';
import { useContext, useEffect, useState } from 'react';
import IssueItem from './IssueItem/IssueItem';
import TableToolBar from './TableToolBar/TableToolBar';
import {
  MainPageContext,
  MainPageDispatchContext,
  mainPageInitialState,
} from '../../context/MainPage/MainPageContext';
import { Button } from '../common';
import { OPENED, RESET } from '../../constants';
import { ReactComponent as xSquare } from '../../assets/xSquare.svg';
import { setFilterOption } from '../../context/MainPage/MainPageActions';
import { CheckBoxActive, CheckBoxDisabled, CheckBoxInitial } from './CheckBox';
import IssueListFilters from './TableToolBar/IssueListFilters';
import IssueListModifier from './TableToolBar/IssueListModifier';
import Pagination from './Pagination';

const IssueTable = () => {
  const { issues, filterOptions } = useContext(MainPageContext);
  const dispatch = useContext(MainPageDispatchContext);
  const [selectedIssues, setSelectedIssues] = useState([]);

  useEffect(() => {
    setSelectedIssues([]);
  }, [issues]);

  const addSelectedIssue = (issueNumber, checked) => {
    if (checked) {
      setSelectedIssues((prevSelectedIssues) => [
        ...prevSelectedIssues,
        issueNumber,
      ]);
    } else {
      setSelectedIssues((prevSelectedIssues) => {
        return prevSelectedIssues.filter((number) => number !== issueNumber);
      });
    }
  };

  const handleAllSelectedIssue = (checked) => {
    if (checked) {
      const allIssueNumbers = issues.map((issue) => issue.number);
      setSelectedIssues(allIssueNumbers);
    } else {
      setSelectedIssues([]);
    }
  };

  const isFilterApplied = Object.values(filterOptions).some(
    (option) => option !== null
      && option !== false
      && option.length > 0
      && option !== OPENED,
  );

  const issueItems = issues
    && issues.length !== 0
    && issues
      .sort(
        (prevIssueInfo, nextIssueInfo) => new Date(prevIssueInfo.lastUpdatedDate)
          - new Date(nextIssueInfo.lastUpdatedDate),
      )
      .map((issue) => (
        <IssueItem
          key={issue.number}
          {...issue}
          handleCheckBoxClick={addSelectedIssue}
          isChecked={selectedIssues.includes(issue.number)}
        />
      ));

  const isAllSelected = selectedIssues.length === issues.length && selectedIssues.length;
  const isAnySelected = selectedIssues.length > 0;

  let CheckBox;

  if (isAllSelected) {
    CheckBox = (
      <CheckBoxActive
        handleCheckBoxClick={() => handleAllSelectedIssue(false)}
      />
    );
  } else if (isAnySelected) {
    CheckBox = (
      <CheckBoxDisabled
        handleCheckBoxClick={() => handleAllSelectedIssue(true)}
      />
    );
  } else {
    CheckBox = (
      <CheckBoxInitial
        handleCheckBoxClick={() => handleAllSelectedIssue(true)}
      />
    );
  }

  return (
    <div>
      {isFilterApplied ? (
        <FilterNoticeBox>
          <Button
            type="ghostButton"
            size="S"
            gap="7px"
            color="neutralText"
            hoverColor="iconBackgoundBlue"
            onclick={() => {
              dispatch(
                setFilterOption(RESET, mainPageInitialState.filterOptions),
              );
            }}
          >
            <XSquare />
            현재의 검색 필터 및 정렬 지우기
          </Button>
        </FilterNoticeBox>
      ) : (
        ''
      )}
      <TableToolBar>
        {CheckBox}
        {selectedIssues.length ? (
          <IssueListModifier
            selectedIssues={selectedIssues}
            issueListTotalCount={selectedIssues.length}
          />
        ) : (
          <IssueListFilters />
        )}
      </TableToolBar>
      {issueItems ? (
        <>
          <IssueItemList>{issueItems}</IssueItemList>
          <Pagination />
        </>
      ) : (
        <NoticeBox>검색과 일치하는 결과가 없습니다.</NoticeBox>
      )}
    </div>
  );
};

export default IssueTable;

const FilterNoticeBox = styled.div`
  height: 38px;
`;

const XSquare = styled(xSquare)`
  stroke: ${({ theme }) => theme.color.neutralText};
`;

const IssueItemList = styled.ul`
  display: flex;
  flex-direction: column;

  height: auto;
  border-radius: 0px 0px 16px 16px;

  > div {
    height: 100px;
    border-right: 1px solid ${({ theme }) => theme.color.neutralBorder};
    border-left: 1px solid ${({ theme }) => theme.color.neutralBorder};
    border-bottom: 1px solid ${({ theme }) => theme.color.neutralBorder};
  }

  > li:last-child {
    border-radius: 0px 0px 16px 16px;
  }
`;

const NoticeBox = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;

  height: 100px;
  font-weight: ${({ theme }) => theme.fontWeight.regular};
  font-size: ${({ theme }) => theme.fontSize.M.size};
  color: ${({ theme }) => theme.color.neutralTextWeak};
  background-color: ${({ theme }) => theme.color.neutralBackgroundStrong};
  border-right: 1px solid ${({ theme }) => theme.color.neutralBorder};
  border-left: 1px solid ${({ theme }) => theme.color.neutralBorder};
  border-bottom: 1px solid ${({ theme }) => theme.color.neutralBorder};
  border-radius: 0px 0px 16px 16px;
`;
