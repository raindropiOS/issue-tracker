import { useEffect, useContext, useCallback } from 'react';
import TabBar from '../components/TabBar/TabBar';
import IssueTable from '../components/IssueTable/IssueTable';
import {
  MainPageContext,
  MainPageDispatchContext,
} from '../context/MainPage/MainPageContext';
import { generateQueryString } from '../utils/generateQueryString';
import {
  fetchStart,
  fetchSuccess,
  fetchError,
  setMainPageState,
} from '../context/MainPage/MainPageActions';

const IssueList = () => {
  const { filterOptions } = useContext(MainPageContext);
  const dispatch = useContext(MainPageDispatchContext);

  const queryString = generateQueryString(filterOptions);

  const fetchIssueData = useCallback(async () => {
    dispatch(fetchStart());
    try {
      const url = `http://3.38.73.117:8080/api-fe/issues${queryString}`;

      const response = await fetch(url);
      const mainPagedata = await response.json();

      dispatch(setMainPageState(mainPagedata));
      dispatch(fetchSuccess());
    } catch (error) {
      // TODO(덴): 에러발생시 진행할 로직 구현 고민하기. ex. 개발자향 or 사용자향 UI?
      dispatch(fetchError(error));
    }
  }, [dispatch, filterOptions]);

  useEffect(() => {
    fetchIssueData();
  }, [fetchIssueData]);

  return (
    <>
      <TabBar />
      <IssueTable />
    </>
  );
};

export default IssueList;
