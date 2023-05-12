import { useState } from 'react';
import FilterBar from './components/FilterBar';
import GlobalStyles from './components/GlobalStyles';

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
      <FilterBar filterOptions={filterOptions} />
    </div>
  );
};
export default App;
