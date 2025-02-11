package io.quarkiverse.githubapi.deployment;

import java.util.Arrays;
import java.util.List;

import org.jboss.jandex.DotName;
import org.kohsuke.github.GHAppInstallation;
import org.kohsuke.github.GHAppInstallationToken;
import org.kohsuke.github.GHAuthenticatedAppInstallation;
import org.kohsuke.github.GHAuthorization;
import org.kohsuke.github.GHAutolink;
import org.kohsuke.github.GHBlob;
import org.kohsuke.github.GHBranch;
import org.kohsuke.github.GHBranchProtection;
import org.kohsuke.github.GHCheckRun;
import org.kohsuke.github.GHCheckRunBuilder;
import org.kohsuke.github.GHCheckSuite;
import org.kohsuke.github.GHCodeownersError;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHCommitPointer;
import org.kohsuke.github.GHCompare;
import org.kohsuke.github.GHContent;
import org.kohsuke.github.GHContentUpdateResponse;
import org.kohsuke.github.GHDeployKey;
import org.kohsuke.github.GHEmail;
import org.kohsuke.github.GHError;
import org.kohsuke.github.GHEventInfo;
import org.kohsuke.github.GHEventPayload;
import org.kohsuke.github.GHExternalGroup;
import org.kohsuke.github.GHGistFile;
import org.kohsuke.github.GHHook;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueChanges;
import org.kohsuke.github.GHIssueEvent;
import org.kohsuke.github.GHIssueRename;
import org.kohsuke.github.GHKey;
import org.kohsuke.github.GHLabel;
import org.kohsuke.github.GHLabelChanges;
import org.kohsuke.github.GHMarketplaceAccount;
import org.kohsuke.github.GHMarketplacePendingChange;
import org.kohsuke.github.GHMarketplacePlan;
import org.kohsuke.github.GHMarketplacePurchase;
import org.kohsuke.github.GHMarketplaceUserPurchase;
import org.kohsuke.github.GHMemberChanges;
import org.kohsuke.github.GHMembership;
import org.kohsuke.github.GHMeta;
import org.kohsuke.github.GHNotificationStream;
import org.kohsuke.github.GHObject;
import org.kohsuke.github.GHOrganization;
import org.kohsuke.github.GHProjectsV2ItemChanges;
import org.kohsuke.github.GHPullRequest;
import org.kohsuke.github.GHPullRequestChanges;
import org.kohsuke.github.GHPullRequestCommitDetail;
import org.kohsuke.github.GHPullRequestFileDetail;
import org.kohsuke.github.GHPullRequestReviewCommentReactions;
import org.kohsuke.github.GHRateLimit;
import org.kohsuke.github.GHRef;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHRepositoryChanges;
import org.kohsuke.github.GHRepositoryDiscussion;
import org.kohsuke.github.GHRepositoryStatistics;
import org.kohsuke.github.GHRepositoryTraffic;
import org.kohsuke.github.GHRepositoryVariable;
import org.kohsuke.github.GHStargazer;
import org.kohsuke.github.GHSubscription;
import org.kohsuke.github.GHTag;
import org.kohsuke.github.GHTagObject;
import org.kohsuke.github.GHTeamChanges;
import org.kohsuke.github.GHThread;
import org.kohsuke.github.GHTree;
import org.kohsuke.github.GHTreeEntry;
import org.kohsuke.github.GHVerification;
import org.kohsuke.github.GHWorkflowJob;
import org.kohsuke.github.GHWorkflowRun;
import org.kohsuke.github.GitCommit;
import org.kohsuke.github.GitUser;

final class GitHubApiDotNames {

    // Root objects
    static final DotName GH_EVENT_PAYLOAD = DotName.createSimple(GHEventPayload.class.getName());
    private static final DotName GH_MARKETPLACE_ACCOUNT = DotName.createSimple(GHMarketplaceAccount.class.getName());
    private static final DotName GH_CONTENT = DotName.createSimple(GHContent.class.getName());
    private static final DotName GH_OBJECT = DotName.createSimple(GHObject.class.getName());
    private static final DotName GH_HOOK = DotName.createSimple(GHHook.class.getName());
    private static final DotName GH_REPOSITORY_TRAFFIC = DotName.createSimple(GHRepositoryTraffic.class.getName());
    private static final DotName GH_REPOSITORY_TRAFFIC_DAILY_INFO = DotName
            .createSimple(GHRepositoryTraffic.DailyInfo.class.getName());
    private static final DotName GH_KEY = DotName.createSimple(GHKey.class.getName());
    private static final DotName SEARCH_RESULT = DotName.createSimple("org.kohsuke.github.SearchResult");
    private static final DotName GIT_USER = DotName.createSimple(GitUser.class.getName());
    private static final DotName GH_COMMIT = DotName.createSimple(GHCommit.class.getName());

    static final List<DotName> GH_ROOT_OBJECTS = Arrays.asList(GH_EVENT_PAYLOAD, GH_MARKETPLACE_ACCOUNT, GH_CONTENT,
            GH_OBJECT,
            GH_HOOK, GH_REPOSITORY_TRAFFIC, GH_REPOSITORY_TRAFFIC_DAILY_INFO, GH_KEY, SEARCH_RESULT, GIT_USER,
            GH_COMMIT);

    static final String[] GH_SIMPLE_OBJECTS = new String[] {
            "org.kohsuke.github.GitHubInteractiveObject",
            GHAppInstallation.class.getName() + "$GHAppInstallationRepositoryResult",
            GHAppInstallation.class.getName() + "$GHAppInstallationRepositoryResult",
            "org.kohsuke.github.GHAppInstallationsPage",
            GHAppInstallationToken.class.getName(),
            GHAuthorization.class.getName() + "$App",
            GHAutolink.class.getName(),
            "org.kohsuke.github.GHArtifactsPage",
            GHAuthenticatedAppInstallation.class.getName(),
            GHBlob.class.getName(),
            GHBranch.class.getName(),
            GHBranch.Commit.class.getName(),
            GHBranchProtection.class.getName(),
            GHBranchProtection.AllowDeletions.class.getName(),
            GHBranchProtection.AllowForcePushes.class.getName(),
            GHBranchProtection.AllowForkSyncing.class.getName(),
            GHBranchProtection.BlockCreations.class.getName(),
            "org.kohsuke.github.GHBranchProtectionBuilder$Restrictions",
            "org.kohsuke.github.GHBranchProtectionBuilder$StatusChecks",
            GHBranchProtection.Check.class.getName(),
            GHBranchProtection.EnforceAdmins.class.getName(),
            GHBranchProtection.LockBranch.class.getName(),
            GHBranchProtection.RequiredConversationResolution.class.getName(),
            GHBranchProtection.RequiredLinearHistory.class.getName(),
            GHBranchProtection.RequiredReviews.class.getName(),
            GHBranchProtection.class.getName() + "$RequiredSignatures",
            GHBranchProtection.RequiredStatusChecks.class.getName(),
            GHBranchProtection.Restrictions.class.getName(),
            GHCheckRunBuilder.Action.class.getName(),
            GHCheckRunBuilder.Annotation.class.getName(),
            GHCheckRunBuilder.Image.class.getName(),
            GHCheckRunBuilder.Output.class.getName(),
            GHCheckRun.Output.class.getName(),
            "org.kohsuke.github.GHCheckRunsPage",
            GHCheckSuite.HeadCommit.class.getName(),
            GHCodeownersError.class.getName(),
            "org.kohsuke.github.GHCommitBuilder$UserInfo",
            GHCommit.File.class.getName(),
            "org.kohsuke.github.GHCommitFilesPage",
            GHCommit.Parent.class.getName(),
            GHCommit.ShortInfo.class.getName(),
            GHCommit.Stats.class.getName(),
            GHCommit.class.getName() + "$User",
            GHCommitPointer.class.getName(),
            GHCompare.class.getName(),
            GHCompare.InnerCommit.class.getName(),
            GHCompare.Tree.class.getName(),
            GHContentUpdateResponse.class.getName(),
            GHDeployKey.class.getName(),
            GHEmail.class.getName(),
            GHError.class.getName(),
            GHEventInfo.class.getName(),
            GHEventInfo.GHEventRepository.class.getName(),
            GHEventPayload.CommentChanges.class.getName(),
            GHEventPayload.CommentChanges.GHFrom.class.getName(),
            GHEventPayload.Installation.Repository.class.getName(),
            GHEventPayload.Push.Pusher.class.getName(),
            GHEventPayload.Push.PushCommit.class.getName(),
            GHExternalGroup.class.getName(),
            GHExternalGroup.GHLinkedExternalMember.class.getName(),
            GHExternalGroup.GHLinkedTeam.class.getName(),
            "org.kohsuke.github.GHExternalGroupPage",
            GHGistFile.class.getName(),
            GHIssueChanges.class.getName(),
            GHIssueChanges.GHFrom.class.getName(),
            GHIssueRename.class.getName(),
            GHIssue.PullRequest.class.getName(),
            GHIssueEvent.class.getName(),
            GHLabel.class.getName(),
            GHLabelChanges.class.getName(),
            GHLabelChanges.GHFrom.class.getName(),
            GHMarketplacePendingChange.class.getName(),
            GHMarketplacePlan.class.getName(),
            GHMarketplacePurchase.class.getName(),
            GHMarketplaceUserPurchase.class.getName(),
            GHMemberChanges.class.getName(),
            GHMemberChanges.FromToPermission.class.getName(),
            GHMemberChanges.FromRoleName.class.getName(),
            GHMembership.class.getName(),
            GHMeta.class.getName(),
            GHNotificationStream.class.getName(),
            "org.kohsuke.github.GHOrgHook",
            GHOrganization.RepositoryRole.class.getName(),
            "org.kohsuke.github.GHPermission",
            GHProjectsV2ItemChanges.class.getName(),
            GHProjectsV2ItemChanges.FieldValue.class.getName(),
            GHProjectsV2ItemChanges.FromTo.class.getName(),
            GHProjectsV2ItemChanges.FromToDate.class.getName(),
            GHPullRequest.AutoMerge.class.getName(),
            GHPullRequestChanges.class.getName(),
            GHPullRequestChanges.GHFrom.class.getName(),
            GHPullRequestChanges.GHCommitPointer.class.getName(),
            GHPullRequestCommitDetail.class.getName(),
            GHPullRequestCommitDetail.Commit.class.getName(),
            GHPullRequestCommitDetail.CommitPointer.class.getName(),
            GHPullRequestCommitDetail.Tree.class.getName(),
            GHPullRequestFileDetail.class.getName(),
            "org.kohsuke.github.GHPullRequestReviewBuilder$DraftReviewComment",
            GHPullRequestReviewCommentReactions.class.getName(),
            GHRateLimit.class.getName(),
            GHRateLimit.Record.class.getName(),
            GHRateLimit.UnknownLimitRecord.class.getName(),
            GHRef.class.getName(),
            GHRef.GHObject.class.getName(),
            GHRepositoryChanges.class.getName(),
            GHRepositoryChanges.FromName.class.getName(),
            GHRepositoryChanges.FromOwner.class.getName(),
            GHRepositoryChanges.FromRepository.class.getName(),
            GHRepositoryChanges.Owner.class.getName(),
            GHRepositoryDiscussion.Category.class.getName(),
            "org.kohsuke.github.GHRepository$GHCodeownersErrors",
            GHRepository.class.getName() + "$GHRepoPermission",
            GHRepository.class.getName() + "$Topics",
            GHRepositoryStatistics.class.getName(),
            GHRepositoryStatistics.ContributorStats.Week.class.getName(),
            GHRepositoryStatistics.CodeFrequency.class.getName(),
            GHRepositoryStatistics.PunchCardItem.class.getName(),
            GHRepositoryVariable.class.getName(),
            GHStargazer.class.getName(),
            GHSubscription.class.getName(),
            GHTag.class.getName(),
            GHTagObject.class.getName(),
            GHTeamChanges.class.getName(),
            GHTeamChanges.FromPrivacy.class.getName(),
            GHTeamChanges.FromRepository.class.getName(),
            GHTeamChanges.FromRepositoryPermissions.class.getName(),
            GHTeamChanges.FromString.class.getName(),
            GHThread.class.getName() + "$Subject",
            GHTree.class.getName(),
            "org.kohsuke.github.GHTreeBuilder$DeleteTreeEntry",
            "org.kohsuke.github.GHTreeBuilder$TreeEntry",
            GHTreeEntry.class.getName(),
            GHVerification.class.getName(),
            GHWorkflowJob.Step.class.getName(),
            "org.kohsuke.github.GHWorkflowJobsPage",
            GHWorkflowRun.HeadCommit.class.getName(),
            "org.kohsuke.github.GHWorkflowRunsPage",
            "org.kohsuke.github.GHWorkflowsPage",
            GitCommit.class.getName(),
            GitCommit.class.getName() + "$Tree",
            "org.kohsuke.github.GitHubRequest",
            "org.kohsuke.github.GitHubRequest$Entry",
            "org.kohsuke.github.GitHubResponse",
            "org.kohsuke.github.GitHubSanityCachedValue",
            "org.kohsuke.github.JsonRateLimit",
            org.kohsuke.github.GHBranchSync.class.getName(),
            "org.kohsuke.github.GHPullRequestReviewBuilder$MultilineDraftReviewComment",
            "org.kohsuke.github.GHPullRequestReviewBuilder$ReviewComment",
            "org.kohsuke.github.GHPullRequestReviewBuilder$SingleLineDraftReviewComment",
            org.kohsuke.github.GHRepositoryRule.class.getName(),
            org.kohsuke.github.GHRepositoryRule.BooleanParameter.class.getName(),
            org.kohsuke.github.GHRepositoryRule.CodeScanningTool.class.getName(),
            org.kohsuke.github.GHRepositoryRule.IntegerParameter.class.getName(),
            org.kohsuke.github.GHRepositoryRule.ListParameter.class.getName(),
            org.kohsuke.github.GHRepositoryRule.Parameter.class.getName(),
            org.kohsuke.github.GHRepositoryRule.Parameters.class.getName(),
            org.kohsuke.github.GHRepositoryRule.StatusCheckConfiguration.class.getName(),
            org.kohsuke.github.GHRepositoryRule.StringParameter.class.getName(),
            org.kohsuke.github.GHRepositoryRule.WorkflowFileReference.class.getName(),
            org.kohsuke.github.GHRepositoryTrafficReferralBase.class.getName(),
            org.kohsuke.github.GHRepositoryTrafficTopReferralPath.class.getName(),
            org.kohsuke.github.GHRepositoryTrafficTopReferralSources.class.getName() };

    private GitHubApiDotNames() {
    }
}
