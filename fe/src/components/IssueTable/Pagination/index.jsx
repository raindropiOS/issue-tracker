import { useContext } from 'react';
import styled, { css } from 'styled-components';
import { setFilterOption } from '../../../context/MainPage/MainPageActions';
import {
  MainPageContext,
  MainPageDispatchContext,
} from '../../../context/MainPage/MainPageContext';

const Pagination = () => {
  const { paging } = useContext(MainPageContext);
  const dispatch = useContext(MainPageDispatchContext);
  const { startPage, nowPage, lastPage } = paging;

  const pageList = Array.from(
    { length: lastPage - startPage + 1 },
    (_, index) => index + startPage,
  );

  const pages = pageList.map((pageNumber) => {
    const isCurrentPage = pageNumber === nowPage;
    return (
      <PageBox
        key={pageNumber}
        $isCurrentPage={isCurrentPage}
        onClick={() => {
          dispatch(setFilterOption('page', pageNumber));
        }}
      >
        {pageNumber}
      </PageBox>
    );
  });

  return (
    <PaginationBox>
      <PaginationButton
        disabled={nowPage === startPage}
        onClick={() => {
          dispatch(setFilterOption('page', nowPage - 1));
        }}
      >
        <div>{'<'}</div>
        <div>Previous</div>
      </PaginationButton>
      {pages}
      <PaginationButton
        disabled={nowPage === lastPage}
        onClick={() => {
          dispatch(setFilterOption('page', nowPage + 1));
        }}
      >
        <div>Next</div>
        <div>{'>'}</div>
      </PaginationButton>
    </PaginationBox>
  );
};

export default Pagination;

const PaginationBox = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  height: 50px;
  gap: 3px;
`;

const PaginationButton = styled.button`
  display: flex;
  gap: 12px;

  min-width: 32px;
  padding: 5px 10px;
  font-style: normal;
  line-height: 20px;
  font-size: 16px;
  ${({ disabled, theme }) => {
    return !disabled
      ? css`
          color: ${theme.color.iconBackgoundBlue};
          cursor: pointer;

          &:hover {
            border: 1px solid transparent;
            border-color: ${theme.color.neutralText};
            border-radius: 6px;
          }
        `
      : css`
          color: ${theme.color.neutralTextWeak};
          cursor: pointer;
        `;
  }}
`;

const PageBox = styled.button`
  min-width: 32px;
  padding: 5px 10px;
  font-style: normal;
  line-height: 20px;
  border-radius: 6px;

  ${({ $isCurrentPage, theme }) => {
    return $isCurrentPage
      ? css`
          color: ${theme.color.accentBackgroundWeak};
          background-color: ${theme.color.iconBackgoundBlue};
          cursor: pointer;
        `
      : css`
          color: ${theme.color.neutralText};
          cursor: pointer;

          &:hover {
            border: 1px solid transparent;
            border-color: ${theme.color.neutralText};
          }
        `;
  }};
`;
