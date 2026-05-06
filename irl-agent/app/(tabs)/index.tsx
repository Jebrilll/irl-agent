import React, { useState } from "react";
import { View, Text, Pressable, TextInput, ScrollView, StyleSheet } from "react-native";

const fakeApps = ["TikTok", "Instagram", "YouTube", "Facebook", "X", "WhatsApp"];

export default function App() {
  const [language, setLanguage] = useState<"en" | "ar" | null>(null);
  const [selectedApps, setSelectedApps] = useState<string[]>([]);
  const [message, setMessage] = useState("");

  const toggleApp = (app: string) => {
    setSelectedApps((prev) =>
      prev.includes(app) ? prev.filter((x) => x !== app) : [...prev, app]
    );
  };

  if (!language) {
    return (
      <View style={styles.container}>
        <Text style={styles.title}>IRL Agent</Text>
        <Text style={styles.subtitle}>Choose your language</Text>

        <Pressable style={styles.button} onPress={() => setLanguage("en")}>
          <Text style={styles.buttonText}>English</Text>
        </Pressable>

        <Pressable style={styles.button} onPress={() => setLanguage("ar")}>
          <Text style={styles.buttonText}>العربية</Text>
        </Pressable>
      </View>
    );
  }

  const isArabic = language === "ar";

  return (
    <ScrollView contentContainerStyle={styles.container}>
      <Text style={styles.title}>IRL Agent</Text>

      <Text style={styles.subtitle}>
        {isArabic ? "اختر التطبيقات التي تريد مراقبتها" : "Select apps to monitor"}
      </Text>

      {fakeApps.map((app) => (
        <Pressable
          key={app}
          style={[styles.appItem, selectedApps.includes(app) && styles.selected]}
          onPress={() => toggleApp(app)}
        >
          <Text style={styles.appText}>{app}</Text>
          <Text>{selectedApps.includes(app) ? "✓" : ""}</Text>
        </Pressable>
      ))}

      <Text style={styles.subtitle}>
        {isArabic ? "رسالة التنبيه" : "Notification message"}
      </Text>

      <TextInput
        style={styles.input}
        placeholder={isArabic ? "اترك الهاتف قليلاً..." : "Put the phone down..."}
        value={message}
        onChangeText={setMessage}
        multiline
      />

      <Pressable style={styles.button}>
        <Text style={styles.buttonText}>{isArabic ? "حفظ" : "Save"}</Text>
      </Pressable>

      <Text style={styles.saved}>
        {isArabic ? "التطبيقات المختارة: " : "Selected apps: "}
        {selectedApps.join(", ") || (isArabic ? "لا شيء" : "None")}
      </Text>
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    flexGrow: 1,
    backgroundColor: "#f5f5f5",
    padding: 24,
    justifyContent: "center",
  },
  title: {
    fontSize: 36,
    fontWeight: "800",
    textAlign: "center",
    marginBottom: 12,
  },
  subtitle: {
    fontSize: 18,
    fontWeight: "600",
    marginTop: 20,
    marginBottom: 12,
    textAlign: "center",
  },
  button: {
    backgroundColor: "#111",
    padding: 16,
    borderRadius: 14,
    marginTop: 12,
  },
  buttonText: {
    color: "#fff",
    textAlign: "center",
    fontSize: 18,
    fontWeight: "700",
  },
  appItem: {
    backgroundColor: "#fff",
    padding: 16,
    borderRadius: 12,
    marginBottom: 10,
    flexDirection: "row",
    justifyContent: "space-between",
  },
  selected: {
    borderWidth: 2,
    borderColor: "#111",
  },
  appText: {
    fontSize: 17,
  },
  input: {
    backgroundColor: "#fff",
    minHeight: 100,
    borderRadius: 12,
    padding: 14,
    fontSize: 16,
    textAlignVertical: "top",
  },
  saved: {
    marginTop: 20,
    textAlign: "center",
    fontSize: 15,
  },
});
     