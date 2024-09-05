import java.awt.Image;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class VoteManager {
    private static ArrayList<String> posts = new ArrayList<>();
    private static HashMap<String, ArrayList<String>> candidates = new HashMap<>();
    private static HashMap<String, HashMap<String, Integer>> votes = new HashMap<>();
    private static HashMap<String, HashSet<String>> voters = new HashMap<>();
    private static HashSet<String> registeredCandidates = new HashSet<>(); // Track registered candidates

    public static void setPosts(ArrayList<String> postNames) {
        posts = postNames;
        for (String post : posts) {
            candidates.put(post, new ArrayList<>());
            votes.put(post, new HashMap<>());
            voters.put(post, new HashSet<>());
        }
    }

    public static ArrayList<String> getPosts() {
        return posts;
    }

    public static void registerCandidate(String post, String name, String dob, String fatherName, String motherName,
                                         String presentAddress, String permanentAddress, String qualification,
                                         String nationality, String bloodGroup) {
        if (!registeredCandidates.contains(name)) {
            ArrayList<String> postCandidates = candidates.get(post);
            postCandidates.add(name);
            votes.get(post).put(name, 0);
            registeredCandidates.add(name);  // Mark candidate as registered
        }
    }

    public static boolean isCandidateRegistered(String name) {
        return registeredCandidates.contains(name);
    }

    public static ArrayList<String> getCandidates(String post) {
        return candidates.get(post);
    }

    public static String castVote(String nid, String post, String candidateName) {
        if (voters.get(post).contains(nid)) {
            return "Your vote has already been casted for " + post;
        } else {
            voters.get(post).add(nid);
            int currentVotes = votes.get(post).get(candidateName);
            votes.get(post).put(candidateName, currentVotes + 1);
            return "Your vote has successfully been casted for " + post;
        }
    }

    public static boolean isVotingFinished() {
        return true; // Placeholder, should be dynamic
    }

    public static String getResults() {
         // Load the background image
        
        StringBuilder results = new StringBuilder("\n\n\nVoting Results:\n");
        for (String post : posts) {
            results.append("Post: ").append(post).append("\n");
            String winner = null;
            int maxVotes = 0;
            for (String candidate : candidates.get(post)) {
                int candidateVotes = votes.get(post).get(candidate);
                results.append(candidate).append(": ").append(candidateVotes).append(" votes\n");
                if (candidateVotes > maxVotes) {
                    maxVotes = candidateVotes;
                    winner = candidate;
                }
            }
            results.append("Winner: ").append(winner).append("\n\n");
        }
        return results.toString();
    }
}
