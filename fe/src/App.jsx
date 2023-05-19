import { useState, useEffect } from 'react';
import { ThemeProvider } from 'styled-components';
import { PageLayout } from './components/common';
import FilterBar from './components/FilterBar/FilterBar';
import GlobalStyles from './style/GlobalStyles';
import IssueTable from './components/IssueTable/IssueTable';
import { darkTheme, lightTheme } from './style';

const App = () => {
  const [theme, setTheme] = useState(lightTheme);
  // TODO: 전체 내용 MainPage로 빼고 App은 라우터 설정
  const [issues, setIssues] = useState([]);
  const [counts, setCounts] = useState({
    openedIssueCount: 2,
    closedIssueCount: 0,
    totalLabelCount: 0,
    milestoneCount: 0,
  });
  const [filterOptions, setFilterOptions] = useState({
    isOpened: true,
    // writtenByMe: false,
    // assignedToMe: false,
    // commentedByMe: false,
    // assignee: null,
    // labels: [],
    // milestone: null,
    // ? author value 배열?
    // author: null,
  });

  // const filteredIssues = issues.filter(
  //   ({ state }) => state === filterOptions.isOpened,
  // );

  useEffect(() => {
    fetch('http://3.38.73.117:8080/api-fe/issues')
      .then((res) => res.json())
      .then((data) => {
        setIssues(data.issues);
      });
  }, []);

  return (
    <div>
      <GlobalStyles />
      <ThemeProvider
        theme={{
          ...theme,
          setTheme: () => {
            setTheme(theme.id === 'lightTheme' ? darkTheme : lightTheme);
          },
        }}
      >
        <PageLayout>
          <FilterBar
            filterOptions={filterOptions}
            setFilterOptions={setFilterOptions}
          />
          <IssueTable
            issues={issues}
            openedIssueCount={counts.openedIssueCount}
            closedIssueCount={counts.closedIssueCount}
            isOpened={filterOptions.isOpened}
            setFilterOptions={setFilterOptions}
          />
        </PageLayout>
      </ThemeProvider>
    </div>
  );
};

export default App;
