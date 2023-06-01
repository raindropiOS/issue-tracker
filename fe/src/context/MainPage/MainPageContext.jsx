import React, { createContext, useReducer } from 'react';
import { OPENED } from '../../constants';
import {
  checkDuplicateAndReturnValue,
  updateArrayWithDuplicateCheck,
} from '../../utils/utils';
import {
  SET_ISSUE_STATE_FILTER_OPTION,
  SET_WRITTEN_BY_ME_FILTER_OPTION,
  SET_ASSIGNED_TO_ME_FILTER_OPTION,
  SET_COMMENTED_BY_ME_FILTER_OPTION,
  SET_ASSIGNEES_FILTER_OPTION,
  SET_LABELS_FILTER_OPTION,
  SET_MILESTONE_NAME_FILTER_OPTION,
  SET_AUTHOR_FILTER_OPTION,
  SET_PAGE_FILTER_OPTION,
  SET_MAIN_PAGE_STATE,
  FETCH_START,
  FETCH_SUCCESS,
  FETCH_ERROR,
  RESET_FILTER_OPTIONS,
} from './MainPageActionTypes';

export const MainPageContext = createContext(null);
export const MainPageDispatchContext = createContext(null);

const mainPageReducer = (state, action) => {
  // ? reduce 내부에서 util 함수를 호출해도 되는걸까? 순수성?
  switch (action.type) {
    case SET_MAIN_PAGE_STATE:
      return {
        ...action.payload,
        filterOptions: state.filterOptions,
        loading: state.loading,
      };
    case FETCH_START:
      return {
        ...state,
        loading: true,
      };
    case FETCH_SUCCESS:
      return {
        ...state,
        loading: false,
      };
    case FETCH_ERROR:
      return {
        ...state,
        loading: false,
      };
    case RESET_FILTER_OPTIONS:
      return {
        ...state,
        filterOptions: {
          ...action.payload,
        },
      };
    case SET_ISSUE_STATE_FILTER_OPTION:
      return {
        ...state,
        filterOptions: {
          ...state.filterOptions,
          cntPage: 1,
          issueState: action.payload,
        },
      };
    case SET_WRITTEN_BY_ME_FILTER_OPTION:
      return {
        ...state,
        filterOptions: {
          ...state.filterOptions,
          cntPage: 1,
          writtenByMe: action.payload,
        },
      };
    case SET_ASSIGNED_TO_ME_FILTER_OPTION:
      return {
        ...state,
        filterOptions: {
          ...state.filterOptions,
          cntPage: 1,
          assignedToMe: action.payload,
        },
      };
    case SET_COMMENTED_BY_ME_FILTER_OPTION:
      return {
        ...state,
        filterOptions: {
          ...state.filterOptions,
          cntPage: 1,
          commentedByMe: action.payload,
        },
      };
    case SET_ASSIGNEES_FILTER_OPTION:
      return {
        ...state,
        filterOptions: {
          ...state.filterOptions,
          cntPage: 1,
          assignees: updateArrayWithDuplicateCheck(
            state.filterOptions.assignees,
            action.payload,
          ),
        },
      };
    case SET_LABELS_FILTER_OPTION:
      return {
        ...state,
        filterOptions: {
          ...state.filterOptions,
          cntPage: 1,
          labels: updateArrayWithDuplicateCheck(
            state.filterOptions.labels,
            action.payload,
          ),
        },
      };
    case SET_MILESTONE_NAME_FILTER_OPTION:
      return {
        ...state,
        filterOptions: {
          ...state.filterOptions,
          cntPage: 1,
          milestoneName: checkDuplicateAndReturnValue(
            state.filterOptions.milestoneName,
            action.payload,
          ),
        },
      };
    case SET_AUTHOR_FILTER_OPTION:
      return {
        ...state,
        filterOptions: {
          ...state.filterOptions,
          cntPage: 1,
          author: checkDuplicateAndReturnValue(
            state.filterOptions.author,
            action.payload,
          ),
        },
      };
    case SET_PAGE_FILTER_OPTION:
      return {
        ...state,
        filterOptions: {
          ...state.filterOptions,
          cntPage: action.payload,
        },
      };
    default:
      throw new Error(`Unknown action: ${action.type}`);
  }
};

// TODO(덴): 프로젝트 기간 안에 못하더라도 꼭 분리해보기!
export const mainPageInitialState = {
  issues: [],
  counts: {},
  allUsers: [],
  allLabels: [],
  allMilestones: [],
  paging: {},
  filterOptions: {
    issueState: OPENED,
    writtenByMe: false,
    assignedToMe: false,
    commentedByMe: false,
    assignees: [],
    labels: [],
    milestoneName: null,
    author: null,
    cntPage: 1,
  },
  loading: false,
};

const MainPageProvider = ({ children }) => {
  const [mainPageState, mainPageDispatch] = useReducer(
    mainPageReducer,
    mainPageInitialState,
  );

  return (
    <MainPageContext.Provider value={mainPageState}>
      <MainPageDispatchContext.Provider value={mainPageDispatch}>
        {children}
      </MainPageDispatchContext.Provider>
    </MainPageContext.Provider>
  );
};

export default MainPageProvider;
