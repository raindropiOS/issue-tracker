import { useState } from 'react';
import { PageLayout } from './components/common';
import FilterBar from './components/FilterBar/FilterBar';
import GlobalStyles from './components/GlobalStyles';
import IssueTable from './components/IssueTable';

const App = () => {
  const [issues, setIssues] = useState([]);
  const [counts, setCounts] = useState({
    openedIssueCount: 0,
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

  return (
    <div>
      <GlobalStyles />
      <PageLayout>
        <FilterBar filterOptions={filterOptions} />
        <IssueTable
          openedIssueCount={counts.openedIssueCount}
          closedIssueCount={counts.closedIssueCount}
          isOpened={filterOptions.isOpened}
        />
      </PageLayout>
    </div>
  );
};
export default App;
