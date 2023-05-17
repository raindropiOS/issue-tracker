import { useState } from 'react';
import { ThemeProvider } from 'styled-components';
import { PageLayout } from './components/common';
import FilterBar from './components/FilterBar/FilterBar';
import GlobalStyles from './style/GlobalStyles';
import IssueTable from './components/IssueTable/IssueTable';
import { darkTheme, lightTheme } from './style';

const initialState = [
  {
    number: 1,
    title: '테스트 이슈 제목1',
    state: true,
    created_date: '2023-02-21 11:28:37',
    labels: [
      {
        name: 'documentation',
        text_color: '#FEFEFE',
        background_color: '#004DE3',
      },
      {
        name: 'bugfix',
        text_color: '#14142B',
        background_color: '#EFF0F6',
      },
    ],
    milestone: { name: '그룹프로젝트: 이슈트래커' },
    author: {
      nickname: 'den',
      image: '이미지파일자체',
    },
  },
  {
    number: 33,
    title: '테스트 이슈 제목2',
    state: true,
    created_date: '2023-05-09 17:18:11',
    labels: [
      {
        name: 'frontend',
        text_color: '#ffffff',
        background_color: '#48d900',
      },
    ],
    milestone: { name: '그룹프로젝트: 이슈트래커' },
    author: {
      nickname: 'caesar',
      image: '이미지파일자체',
    },
  },
];

const App = () => {
  const [theme, setTheme] = useState(lightTheme);
  // TODO: 전체 내용 MainPage로 빼고 App은 라우터 설정
  const [issues, setIssues] = useState(initialState);
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

  const filteredIssues = issues.filter(
    ({ state }) => state === filterOptions.isOpened,
  );

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
          <FilterBar filterOptions={filterOptions} />
          <IssueTable
            issues={filteredIssues}
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
