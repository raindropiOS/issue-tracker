import { useState } from 'react';
import { PageLayout } from './components/common';
import FilterBar from './components/FilterBar/FilterBar';
import GlobalStyles from './components/GlobalStyles';
import IssueTable from './components/IssueTable/IssueTable';

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
    // author: null,
  });

  const filteredIssues = issues.filter(
    ({ state }) => state === filterOptions.isOpened,
  );

  return (
    <div>
      <GlobalStyles />
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
    </div>
  );
};
export default App;
