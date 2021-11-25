package com.zccadwait.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;
import java.util.List;

/**
 * Model object to track a ticket.
 *
 * @author Adwait Bauskar
 */
public class Ticket{

    /**
     * This wrapper is introduced as the API returns a nested object.
     */
    private class TicketWrapper{
        Ticket ticket;

        public Ticket getTicket() {
            return ticket;
        }

        public void setTicket(Ticket ticket) {
            this.ticket = ticket;
        }
    }

    private static final GsonBuilder builder = new GsonBuilder();
    private static final Gson gson = builder.create();

    public static Ticket parseTicket(String ticket){
        return Ticket.gson.fromJson(ticket, TicketWrapper.class).getTicket();
    }

    private String url;
    private int id;
    private Object external_id;
    private Date created_at;
    private Date updated_at;
    private String type;
    private String subject;
    private String raw_subject;
    private String description;
    private String priority;
    private String status;
    private Object recipient;
    private long requester_id;
    private long submitter_id;
    private long assignee_id;
    private Object organization_id;
    private long group_id;
    private List<Object> collaborator_ids;
    private List<Object> follower_ids;
    private List<Object> email_cc_ids;
    private Object forum_topic_id;
    private Object problem_id;
    private boolean has_incidents;
    private boolean is_public;
    private Object due_at;
    private List<String> tags;
    private List<Object> custom_fields;
    private Object satisfaction_rating;
    private List<Object> sharing_agreement_ids;
    private List<Object> fields;
    private List<Object> followup_ids;
    private long ticket_form_id;
    private long brand_id;
    private boolean allow_channelback;
    private boolean allow_attachments;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getExternalId() {
        return external_id;
    }

    public void setExternalId(Object external_id) {
        this.external_id = external_id;
    }

    public Date getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdatedAt() {
        return updated_at;
    }

    public void setUpdatedAt(Date updated_at) {
        this.updated_at = updated_at;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRawSubject() {
        return raw_subject;
    }

    public void setRawSubject(String raw_subject) {
        this.raw_subject = raw_subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getRecipient() {
        return recipient;
    }

    public void setRecipient(Object recipient) {
        this.recipient = recipient;
    }

    public long getRequesterId() {
        return requester_id;
    }

    public void setRequesterId(long requester_id) {
        this.requester_id = requester_id;
    }

    public long getSubmitterId() {
        return submitter_id;
    }

    public void setSubmitterId(long submitter_id) {
        this.submitter_id = submitter_id;
    }

    public long getAssigneeId() {
        return assignee_id;
    }

    public void setAssigneeId(long assignee_id) {
        this.assignee_id = assignee_id;
    }

    public Object getOrganizationId() {
        return organization_id;
    }

    public void setOrganizationId(Object organization_id) {
        this.organization_id = organization_id;
    }

    public long getGroupId() {
        return group_id;
    }

    public void setGroupId(long group_id) {
        this.group_id = group_id;
    }

    public List<Object> getCollaboratorIds() {
        return collaborator_ids;
    }

    public void setCollaboratorIds(List<Object> collaborator_ids) {
        this.collaborator_ids = collaborator_ids;
    }

    public List<Object> getFollowerIds() {
        return follower_ids;
    }

    public void setFollowerIds(List<Object> follower_ids) {
        this.follower_ids = follower_ids;
    }

    public List<Object> getEmailCcIds() {
        return email_cc_ids;
    }

    public void setEmailCcIds(List<Object> email_cc_ids) {
        this.email_cc_ids = email_cc_ids;
    }

    public Object getForumTopicId() {
        return forum_topic_id;
    }

    public void setForumTopicId(Object forum_topic_id) {
        this.forum_topic_id = forum_topic_id;
    }

    public Object getProblemId() {
        return problem_id;
    }

    public void setProblemId(Object problem_id) {
        this.problem_id = problem_id;
    }

    public boolean isHasIncidents() {
        return has_incidents;
    }

    public void setHasIncidents(boolean has_incidents) {
        this.has_incidents = has_incidents;
    }

    public boolean isPublic() {
        return is_public;
    }

    public void setIsPublic(boolean is_public) {
        this.is_public = is_public;
    }

    public Object getDueAt() {
        return due_at;
    }

    public void setDueAt(Object due_at) {
        this.due_at = due_at;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Object> getCustomFields() {
        return custom_fields;
    }

    public void setCustomFields(List<Object> custom_fields) {
        this.custom_fields = custom_fields;
    }

    public Object getSatisfactionRating() {
        return satisfaction_rating;
    }

    public void setSatisfactionRating(Object satisfaction_rating) {
        this.satisfaction_rating = satisfaction_rating;
    }

    public List<Object> getSharingAgreementIds() {
        return sharing_agreement_ids;
    }

    public void setSharingAgreementIds(List<Object> sharing_agreement_ids) {
        this.sharing_agreement_ids = sharing_agreement_ids;
    }

    public List<Object> getFields() {
        return fields;
    }

    public void setFields(List<Object> fields) {
        this.fields = fields;
    }

    public List<Object> getFollowupIds() {
        return followup_ids;
    }

    public void setFollowupIds(List<Object> followup_ids) {
        this.followup_ids = followup_ids;
    }

    public long getTicketFormId() {
        return ticket_form_id;
    }

    public void setTicketFormId(long ticket_form_id) {
        this.ticket_form_id = ticket_form_id;
    }

    public long getBrandId() {
        return brand_id;
    }

    public void setBrandId(long brand_id) {
        this.brand_id = brand_id;
    }

    public boolean isAllowChannelback() {
        return allow_channelback;
    }

    public void setAllowChannelback(boolean allow_channelback) {
        this.allow_channelback = allow_channelback;
    }

    public boolean isAllowAttachments() {
        return allow_attachments;
    }

    public void setAllowAttachments(boolean allow_attachments) {
        this.allow_attachments = allow_attachments;
    }
}


