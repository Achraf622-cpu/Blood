package com.donner.service;

import com.donner.model.enums.GroupeSanguin;
import java.util.*;

public class BloodCompatibilityService {
    
    private static final Map<GroupeSanguin, Set<GroupeSanguin>> COMPATIBILITY_MAP = new HashMap<>();
    
    static {
        // Receveur O- peut recevoir de O-
        COMPATIBILITY_MAP.put(GroupeSanguin.O_NEGATIF, Collections.singleton(GroupeSanguin.O_NEGATIF));
        
        // Receveur O+ peut recevoir de O- et O+
        COMPATIBILITY_MAP.put(GroupeSanguin.O_POSITIF, new HashSet<>(Arrays.asList(
            GroupeSanguin.O_NEGATIF, GroupeSanguin.O_POSITIF)));
        
        // Receveur A- peut recevoir de O- et A-
        COMPATIBILITY_MAP.put(GroupeSanguin.A_NEGATIF, new HashSet<>(Arrays.asList(
            GroupeSanguin.O_NEGATIF, GroupeSanguin.A_NEGATIF)));
        
        // Receveur A+ peut recevoir de O-, O+, A-, A+
        COMPATIBILITY_MAP.put(GroupeSanguin.A_POSITIF, new HashSet<>(Arrays.asList(
            GroupeSanguin.O_NEGATIF, GroupeSanguin.O_POSITIF, 
            GroupeSanguin.A_NEGATIF, GroupeSanguin.A_POSITIF)));
        
        // Receveur B- peut recevoir de O- et B-
        COMPATIBILITY_MAP.put(GroupeSanguin.B_NEGATIF, new HashSet<>(Arrays.asList(
            GroupeSanguin.O_NEGATIF, GroupeSanguin.B_NEGATIF)));
        
        // Receveur B+ peut recevoir de O-, O+, B-, B+
        COMPATIBILITY_MAP.put(GroupeSanguin.B_POSITIF, new HashSet<>(Arrays.asList(
            GroupeSanguin.O_NEGATIF, GroupeSanguin.O_POSITIF, 
            GroupeSanguin.B_NEGATIF, GroupeSanguin.B_POSITIF)));
        
        // Receveur AB- peut recevoir de O-, A-, B-, AB-
        COMPATIBILITY_MAP.put(GroupeSanguin.AB_NEGATIF, new HashSet<>(Arrays.asList(
            GroupeSanguin.O_NEGATIF, GroupeSanguin.A_NEGATIF, 
            GroupeSanguin.B_NEGATIF, GroupeSanguin.AB_NEGATIF)));
        
        // Receveur AB+ peut recevoir de tous les groupes (donneur universel)
        COMPATIBILITY_MAP.put(GroupeSanguin.AB_POSITIF, new HashSet<>(Arrays.asList(
            GroupeSanguin.O_NEGATIF, GroupeSanguin.O_POSITIF,
            GroupeSanguin.A_NEGATIF, GroupeSanguin.A_POSITIF,
            GroupeSanguin.B_NEGATIF, GroupeSanguin.B_POSITIF,
            GroupeSanguin.AB_NEGATIF, GroupeSanguin.AB_POSITIF)));
    }
    
    /**
     * Vérifie si un donneur est compatible avec un receveur
     * @param donneurGroupe Le groupe sanguin du donneur
     * @param receveurGroupe Le groupe sanguin du receveur
     * @return true si compatible, false sinon
     */
    public static boolean isCompatible(GroupeSanguin donneurGroupe, GroupeSanguin receveurGroupe) {
        Set<GroupeSanguin> compatibleGroups = COMPATIBILITY_MAP.get(receveurGroupe);
        return compatibleGroups != null && compatibleGroups.contains(donneurGroupe);
    }
    
    /**
     * Retourne tous les groupes sanguins compatibles avec un receveur donné
     * @param receveurGroupe Le groupe sanguin du receveur
     * @return Set des groupes sanguins compatibles
     */
    public static Set<GroupeSanguin> getCompatibleGroups(GroupeSanguin receveurGroupe) {
        return COMPATIBILITY_MAP.getOrDefault(receveurGroupe, java.util.Collections.emptySet());
    }
}
