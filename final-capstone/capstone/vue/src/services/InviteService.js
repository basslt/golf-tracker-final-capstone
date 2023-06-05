import axios from 'axios';


export default {

    getAllInvites() {
        return axios.get(`/invites`);
    },

    getInviteById(inviteId) {
        return axios.get(`/invites/${inviteId}`);
    },

    getSentInvitesByUser(userId) {
        return axios.get(`/users/${userId}/sentinvites`);
    },

    getReceivedInvitesByUser(userId) {
        return axios.get(`/users/${userId}/receivedinvites`);
    },

    createInvite(invite) {
        return axios.post(`/invites`, invite);
    },

    updateInvite(inviteId, invite) {
        return axios.put(`/invites/${inviteId}`, invite);
    },

    deleteInvite(inviteId) {
        return axios.delete(`/invites/${inviteId}`);
    }

};